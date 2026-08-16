package com.estatehub.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingFilter implements GlobalFilter, Ordered {

    private final Map<String, TokenBucket> buckets = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String ip = getClientIp(exchange);
        
        TokenBucket bucket = buckets.computeIfAbsent(ip, k -> new TokenBucket());
        
        if (bucket.tryConsume()) {
            return chain.filter(exchange);
        } else {
            return onRateLimitExceeded(exchange);
        }
    }

    private String getClientIp(ServerWebExchange exchange) {
        InetSocketAddress remoteAddress = exchange.getRequest().getRemoteAddress();
        if (remoteAddress != null && remoteAddress.getAddress() != null) {
            return remoteAddress.getAddress().getHostAddress();
        }
        return "unknown-ip";
    }

    private Mono<Void> onRateLimitExceeded(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        
        String body = "{\"error\": \"Too Many Requests\", \"message\": \"Rate limit of 100 requests per minute exceeded.\"}";
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        
        return response.writeWith(Mono.just(response.bufferFactory().wrap(bytes)));
    }

    @Override
    public int getOrder() {
        // Run first in the filter chain
        return Ordered.HIGHEST_PRECEDENCE;
    }

    private static class TokenBucket {
        private final double capacity = 100.0;
        private final double refillRatePerSecond = 100.0 / 60.0; // refills 100 tokens per 60 seconds
        private double tokens;
        private long lastRefillTime;

        public TokenBucket() {
            this.tokens = capacity;
            this.lastRefillTime = System.currentTimeMillis();
        }

        public synchronized boolean tryConsume() {
            refill();
            if (tokens >= 1.0) {
                tokens -= 1.0;
                return true;
            }
            return false;
        }

        private void refill() {
            long now = System.currentTimeMillis();
            double deltaSeconds = (now - lastRefillTime) / 1000.0;
            double refillTokens = deltaSeconds * refillRatePerSecond;
            if (refillTokens > 0) {
                tokens = Math.min(capacity, tokens + refillTokens);
                lastRefillTime = now;
            }
        }
    }
}
