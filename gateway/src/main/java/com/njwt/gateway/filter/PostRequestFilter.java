package com.njwt.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.njwt.common.jwt.JWTResult;
import com.njwt.common.jwt.JWTUtils;
import com.njwt.gateway.config.FilterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class PostRequestFilter extends ZuulFilter {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FilterConfig filterConfig;
    @Override
    public String filterType() {
        return "post";
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
        Map<String,String> map= ctx.getZuulRequestHeaders();
        logger.error("getZuulRequestHeaders:{}",map.get("username"));
        logger.error("url:{};token:{}",uri,token);
        JWTResult result = JWTUtils.validateJWT(token);
        String newToken = JWTUtils.createJWT(result.getClaims().getId(),
                result.getClaims().getIssuer(), result.getClaims().getSubject(),
                1*60*1000);
        logger.error("newtoken:{}",newToken);
        ctx.addZuulRequestHeader("authtokena", newToken);
        ctx.addZuulResponseHeader("authtoken",newToken);

        return null;
    }
}
