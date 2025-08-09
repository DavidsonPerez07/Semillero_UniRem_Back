package com.unirem.auth_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/auth/login", "/auth/register").permitAll()
                        //.anyRequest().authenticated()
                        .anyRequest().permitAll()
                );
                //.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                //.formLogin(login -> login.disable())
                //.httpBasic(basic -> basic.disable());

        return http.build();
    }
}
