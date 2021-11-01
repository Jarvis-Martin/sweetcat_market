package com.sweetcat.gateway.globalFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/5-15:50
 * @Version: 1.0
 */
@Component
public class RewritePathGlobalFilter implements GlobalFilter, Ordered {
    @Value("${api-version-str}")
    private String apiVersionStr;

    private  final Logger logger = LoggerFactory.getLogger(RewritePathGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Custom GlobalFilter: RewritePathGlobalFilter /api/v1/xx => /xx");
        ServerHttpRequest request = exchange.getRequest();
        if (request.getPath().toString().startsWith(apiVersionStr)) {
            // 从原 request 的 path 截取出正在的 paht（如 /api/v1/xx => /xx),并设置到新 request 中
            ServerHttpRequest newReq = request.mutate().path(request.getPath().toString().substring(7)).build();
            logger.info("Request path after rewrite by RewritePathGlobalFilter: " + newReq.getPath().toString());
            // 用 newReq 替换 exhange 中的原request
            ServerWebExchange build = exchange.mutate().request(newReq).build();
            return chain.filter(build);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
