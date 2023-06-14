package com.backend.apigateway.config;

import com.backend.apigateway.UserDTO;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<Object> {




    Logger log = LoggerFactory.getLogger(AuthFilter.class);

    private final WebClient.Builder webClientBuilder;

    public AuthFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new RuntimeException("Missing authorization header");
            }

            String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(org.springframework.http.HttpHeaders.AUTHORIZATION)).get(0);
            String[] parts = authHeader.split(" ");
            log.info(parts[0]);
            log.info(parts[1]);
            if (parts.length !=2 || !"Bearer".equals(parts[0])) {
                throw new RuntimeException("Incorrect authorization structure");
            }

            log.info("Hello there");

            Mono<UserDTO> originalRequest = webClientBuilder.build()
                    .get()
                    .uri("http://security-service.default.svc.cluster.local/api/auth/validate?token=" + parts[1])
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), resp -> Mono.error(new RuntimeException("Unexpected status")))
                    .bodyToMono(UserDTO.class);


            return originalRequest
                    .map(userDTO -> {
                        exchange.getRequest()
                                .mutate()
                                .header("x-auth-user-id", String.valueOf(userDTO.getId()));
                        return exchange;
                    })
                    .flatMap(chain::filter);
        };
    }

    public static class Config {

    }
}
