package com.backend.apigateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;

@Configuration
public class GatewayConfig {


    private static final String MICROSERVICE = "lb://music-service";

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/songs/**")
                        .filters(f -> f.filter(filterFunction()))
                        .uri(MICROSERVICE))
                .route(r -> r.path("/auth/**")
                        .filters(f -> f.filter(filterFunction()))
                        .uri("lb://security-service"))
                .route(r -> r.path("/users/**")
                        .filters(f -> f.filter(filterFunction()))
                        .uri("lb://user-service"))
                .route(r -> r.path("/communities/**")
                        .filters(f -> f.filter(filterFunction()))
                        .uri("lb://user-service"))
                .route(r -> r.path("/playlists/**")
                        .filters(f -> f.filter(filterFunction()))
                        .uri(MICROSERVICE))
                .route(r -> r.path("/albums/**")
                        .filters(f -> f.filter(filterFunction()))
                        .uri(MICROSERVICE))
                .route(r -> r.path("/messages/**")
                        .filters(f -> f.filter(filterFunction()))
                        .uri("lb://message-service"))
                .build();

    }

    private GatewayFilter filterFunction() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header("X-Custom-Header", "API Gateway Header").build();
            return chain.filter(exchange.mutate().request(request).build());
        };
    }
}