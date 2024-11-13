package com.github.diogodelima.usersservice.config

import org.mindrot.jbcrypt.BCrypt
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SecurityConfig {

    @Bean
    fun passwordEncoder(): (String) -> String =  { BCrypt.hashpw(it, BCrypt.gensalt()) }

}