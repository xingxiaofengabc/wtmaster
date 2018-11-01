package com.njwt.cms.client.feign;

import com.njwt.cms.api.service.UserService;
import com.njwt.cms.client.core.config.FeignConfig;
import com.njwt.cms.client.feign.hystrix.UserFeignClientHystrixFallback;

import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name="gateway")

public interface FeignClientUser extends UserService {
}
