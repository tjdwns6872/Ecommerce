package com.simple.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRF 비활성화
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용 안 함
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(
                    "/ecommerce/api/user/login/*"
                    , "/ecommerce/api/user/login"
                    , "/ecommerce/api/user/join"
                    , "/test"
                    , "/swagger-ui/**"
                    , "/v3/api-docs/**").permitAll() // 인증없이 접근 가능한 url패턴
                .anyRequest().authenticated() // 나머지 요청은 인증 필요
            );

        return http.build();
    }
}
