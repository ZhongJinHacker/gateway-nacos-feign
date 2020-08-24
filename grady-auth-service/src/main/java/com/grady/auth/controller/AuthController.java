package com.grady.auth.controller;

import com.grady.auth.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/checkAuth/{id}")
    public CommonResult<Boolean> checkAuth(@PathVariable("id") Long id) {

        logger.info("AuthController: id = " + id);
        return new CommonResult(200, "hello", false);
    }
}
