package com.github.diogodelima.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
@Configuration
class SecurityConfig {

    @Bean
    fun defaultSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {

        return http
            .authorizeExchange { exchange ->
                exchange
                    .pathMatchers(HttpMethod.GET, "/products/secure").authenticated()
                    .pathMatchers(HttpMethod.GET, "/products/free").permitAll()
                    .pathMatchers(HttpMethod.GET, "/products/*").permitAll()
                    .pathMatchers(HttpMethod.GET, "/products/page/*").permitAll()
                    .pathMatchers(HttpMethod.GET, "/users/hello").authenticated()
                    .pathMatchers(HttpMethod.GET, "/users/*").permitAll()
                    .anyExchange().authenticated()
            }
            .oauth2ResourceServer { it.jwt(Customizer.withDefaults()) }
            .csrf { it.disable() }
            .build()
    }

}