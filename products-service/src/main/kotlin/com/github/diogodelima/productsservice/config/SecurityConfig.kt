package com.github.diogodelima.productsservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class SecurityConfig {

    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {

        return http
            .authorizeHttpRequests { request ->
                request
                    .requestMatchers(HttpMethod.GET, "/api/products/*").permitAll()
                    .anyRequest().authenticated()
            }
            .csrf { it.disable() }
            .oauth2ResourceServer { it.jwt(Customizer.withDefaults()) }
            .build()
    }

}