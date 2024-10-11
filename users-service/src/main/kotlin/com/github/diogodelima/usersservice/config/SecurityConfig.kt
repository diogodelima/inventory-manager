package com.github.diogodelima.usersservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {

        return http
            .authorizeHttpRequests { request ->
                request
                    .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
                    .requestMatchers(HttpMethod.POST, "/users/register/google").permitAll()
                    .requestMatchers(HttpMethod.GET, "/users/**").permitAll()
                    .anyRequest().authenticated()
            }
            .csrf { it.disable() }
            .build()
    }

}