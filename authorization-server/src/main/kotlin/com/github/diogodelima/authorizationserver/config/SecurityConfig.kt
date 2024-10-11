package com.github.diogodelima.authorizationserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint

@Configuration
@EnableWebSecurity
class SecurityConfig(

    private val authenticationHandler: AuthenticationSuccessHandler

) {

    @Bean
    @Order(1)
    fun authorizationServerSecurity(http: HttpSecurity): SecurityFilterChain {

        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http)

        http.getConfigurer(OAuth2AuthorizationServerConfigurer::class.java)
            .oidc(Customizer.withDefaults())

        return http.exceptionHandling { exceptions ->
            exceptions.authenticationEntryPoint(
                LoginUrlAuthenticationEntryPoint("/login")
            )
        }.oauth2ResourceServer { resourceServer ->
            resourceServer.jwt(Customizer.withDefaults())
        }.build()

    }

    @Bean
    @Order(2)
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {

        return http
            .authorizeHttpRequests { request ->
                request
                    .anyRequest().authenticated()
            }
            .csrf(Customizer.withDefaults())
            .oauth2Login { oauth2Login ->
                oauth2Login
                    .successHandler(authenticationHandler)
            }
            .formLogin(Customizer.withDefaults())
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}