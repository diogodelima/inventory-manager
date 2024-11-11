package com.github.diogodelima.ordersservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
                    .anyRequest().authenticated()
            }
            .csrf { it.disable() }
            .oauth2ResourceServer { it.jwt(Customizer.withDefaults()) }
            .build()
    }

}