package com.njwt.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.njwt.cms.api.service.UserService;
import com.njwt.common.jwt.JWTResult;
import com.njwt.common.jwt.JWTSubject;
import com.njwt.common.jwt.JWTUtils;
import com.njwt.gateway.config.FilterConfig;
import com.njwt.gateway.feign.FeignClientUser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class CustomAuthFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FilterConfig filterConfig;

    @Resource
    private FeignClientUser feignClientUser;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        String ignoresParam = filterConfig.getIgnores();
        String[] ignoreArray = ignoresParam.split(",");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();//请求路径
        //token (header)
        String token = request.getHeader("authtoken");
        logger.info("URL地址：{}", uri);
        boolean check = true;
        for (int i = 0; i < ignoreArray.length; i++) {

            if (uri.toString().contains(ignoreArray[i])) {
                check = false;
                break;
            }
        }
        if (!check) {
            //ctx.setSendZuulResponse(true);
           // ctx.setResponseStatusCode(200);
            return null;
        }
        //token为空，不是排除外的URL
        if (StringUtils.isBlank(token) && check) {
            System.err.println("没有token...校验失败....");

            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("--------no token !---------");
            try {
                ctx.getResponse().sendRedirect("/tologin");
            } catch (IOException ex) {
                logger.error("登录地址错误：{}", ex.getMessage());
            }

        } else if(!StringUtils.isBlank(token) && !check){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("-------- yes login token !---------");
        }else if(StringUtils.isBlank(token) && !check){

            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("--------no token !---------");
            try {
                ctx.getResponse().sendRedirect("/tologin");
            } catch (IOException ex) {
                logger.error("登录地址错误：{}", ex.getMessage());
            }
        }else {

            // 拿到token  redis
            //token 值
            JWTResult jwtResult = JWTUtils.validateJWT(token);
            //token值不为空，或者是排除的URL地址
            if (jwtResult.isSuccess() && check) {
                JWTSubject jwtSubject=JWTUtils.readValue(jwtResult.getClaims().getSubject());
                // 获取redis用户信息
                String username = feignClientUser.getUserInfo(jwtSubject.getUsername());
                //1 我需要解密:  userId_orgId_roleId
                ctx.addZuulRequestHeader("username", username);

                ctx.addZuulRequestHeader("otherParams", "123");

                logger.info("获取请求头部参数列表：" + ctx.getZuulRequestHeaders().toString());
            } else {
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
                ctx.setResponseBody("--------token auth fail ! ---------");
                return null;
            }
        }

        return null;
    }
}
