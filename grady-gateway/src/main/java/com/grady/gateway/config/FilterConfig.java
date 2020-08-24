package com.grady.gateway.config;

import com.grady.gateway.filter.OpenapiGatewayFilterFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FilterConfig {

    @Bean
    public OpenapiGatewayFilterFactory openapiGatewayFilterFactory() {
        return new OpenapiGatewayFilterFactory();
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
