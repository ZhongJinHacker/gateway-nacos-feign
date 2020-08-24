package com.grady.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthApp8802 {

    public static void main(String[] args) {
        SpringApplication.run(AuthApp8802.class, args);
    }
}
