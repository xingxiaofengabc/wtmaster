package com.njwt.cms.client.core.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
public class FeignConfig implements RequestInterceptor {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public void apply(RequestTemplate requestTemplate) {
        if(getHttpServletRequest()!=null) {
            String token = getHeaders(getHttpServletRequest()).get("x-auth-token");
            if (!StringUtils.isNotBlank(token)) {
                logger.error("x-auth-token:{}",token);
                requestTemplate.header("x-auth-token", token);
            }
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
