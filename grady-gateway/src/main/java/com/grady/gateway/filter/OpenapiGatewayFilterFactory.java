package com.grady.gateway.filter;

import com.grady.gateway.entity.CommonResult;
import com.grady.gateway.service.OpenApiAuthService;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class OpenapiGatewayFilterFactory extends AbstractGatewayFilterFactory<OpenapiGatewayFilterFactory.Config> implements Ordered {

    private final Logger logger = LoggerFactory.getLogger(OpenapiGatewayFilterFactory.class);

    @Autowired
    private OpenApiAuthService openApiAuthService;

    public OpenapiGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            logger.info("openapi filter -----》 config：" + config.toString());
            CommonResult<Boolean> ret = openApiAuthService.checkAuth(999L);
            if (ret.getData() == false) {
                return Mono.defer(() -> {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);//设置status
                    final ServerHttpResponse response = exchange.getResponse();
                    byte[] bytes = "{\"code\":\"99999\",\"message\":\"非法访问,没有检测到token~~~~~~\"}".getBytes(StandardCharsets.UTF_8);
                    DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                    response.getHeaders().set("aaa", "bbb");//设置header
                    logger.info("TokenFilter拦截非法请求，没有检测到token............");
                    return response.writeWith(Flux.just(buffer));//设置body
                });

            }
            return chain.filter(exchange);
        };
    }

    @Override
    public int getOrder() {
        return -100;
    }

    /**
     * 自定义的config类，用来设置传入的参数
     */
    public static class Config {

        /**
         * 过滤类型
         */
        private String type;

        /**
         * 操作
         */
        private String op;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOp() {
            return op;
        }

        public void setOp(String op) {
            this.op = op;
        }
    }
}
