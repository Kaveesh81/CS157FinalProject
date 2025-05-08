package com.mlatsjsu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/me").authenticated()
                        .requestMatchers("/api/v1/users/**").hasRole("MANAGER")
                        .requestMatchers("/api/v1/semesters/**").hasRole("MANAGER")
                        .requestMatchers("/api/v1/projects/**").authenticated()
                        .requestMatchers("/api/v1/projects/*/memberships/**").authenticated()
                        .anyRequest().authenticated())
                .oauth2Login(oauth2 -> {
                });
        return http.build();
    }
}