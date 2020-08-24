package com.grady.gateway.service;

import com.grady.gateway.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "grady-auth-provider")
public interface OpenApiAuthService {

    @GetMapping("/checkAuth/{id}")
    public CommonResult<Boolean> checkAuth(@PathVariable("id") Long id);
}
