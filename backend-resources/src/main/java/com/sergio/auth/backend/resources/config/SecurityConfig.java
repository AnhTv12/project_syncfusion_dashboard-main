package com.sergio.auth.backend.resources.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.mvcMatcher("/**")
                .authorizeRequests()
                .mvcMatchers("/**")
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();
//        http
//                .csrf().disable()
//                .authorizeHttpRequests(author->author.antMatchers("/**").authenticated());
        return http.build();
    }
}
