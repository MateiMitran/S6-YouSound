package com.backend.musicservice.config;

import org.springframework.security.core.Authentication;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class WebSecurity {


    @Autowired
    KeyUtils keyUtils;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/all", "/api/delete/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/api/music/songs/create", "/api/music/songs/upload/**", "/api/music/songs/delete/**", "/api/music/albums/create").hasAuthority("ROLE_ARTIST")
                                .requestMatchers("/actuator/prometheus").permitAll()
                                .anyRequest().hasAuthority("ROLE_USER")
                )
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(new JwtAuthenticationConverter());
        return http.build();
    }

    @Bean
    @Primary
    JwtDecoder jwtAccessTokenDecoder() {
        return NimbusJwtDecoder.withPublicKey(keyUtils.getAccessTokenPublicKey()).build();

    }
    @Bean
    JwtDecoder jwtRefreshTokenDecoder() {
        return NimbusJwtDecoder.withPublicKey(keyUtils.getRefreshTokenPublicKey()).build();
    }

}