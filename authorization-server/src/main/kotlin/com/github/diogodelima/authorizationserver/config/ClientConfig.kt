package com.github.diogodelima.authorizationserver.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.core.oidc.OidcScopes
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings
import java.util.*


/*

    This class could be a service, and maybe I will implement this later

*/

@Configuration
class ClientConfig(

    @Value("\${client.id}")
    private val clientId: String,

    @Value("\${client.secret}")
    private val clientSecret: String,

    private val passwordEncoder: PasswordEncoder

) {

    @Bean
    fun registeredClientRepository(): RegisteredClientRepository {
        val oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId(clientId)
            .clientSecret(passwordEncoder.encode(clientSecret))
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .redirectUri("https://oauth.pstmn.io/v1/callback")
            .scope(OidcScopes.OPENID)
            .scope(OidcScopes.PROFILE)
            .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
            .build()

        return InMemoryRegisteredClientRepository(oidcClient)
    }



}