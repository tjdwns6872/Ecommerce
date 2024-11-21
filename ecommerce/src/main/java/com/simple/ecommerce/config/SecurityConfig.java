package com.simple.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.simple.ecommerce.component.JwtAuthenticationFilter;
import com.simple.ecommerce.util.JwtUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRF 비활성화
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용 안 함
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(
                    "/ecommerce/api/user/login/**"
                    , "/ecommerce/api/user/login"
                    , "/ecommerce/api/user/join"
                    , "/ecommerce/api/user/**"
                    , "/ecommerce/api/sms/**"
                    , "/test"
                    , "/swagger-ui/**"
                    , "/v3/api-docs/**").permitAll() // 인증없이 접근 가능한 url패턴
                .anyRequest().authenticated() // 나머지 요청은 인증 필요
            ).addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
