package com.njwt.gateway.feign;

import com.njwt.cms.api.service.UserService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("server-cms")
public interface FeignClientUser extends UserService {
}
