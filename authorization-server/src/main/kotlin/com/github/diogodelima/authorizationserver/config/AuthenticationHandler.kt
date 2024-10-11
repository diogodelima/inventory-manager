package com.github.diogodelima.authorizationserver.config

import com.github.diogodelima.authorizationserver.dto.UserRegisterWithGoogleDto
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.*
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.client.*

@Component
class AuthenticationHandler(

    private val restTemplate: RestTemplate

) : AuthenticationSuccessHandler {

    private val savedRequestAwareAuthenticationSuccessHandler = SavedRequestAwareAuthenticationSuccessHandler()

    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {

        val principal = authentication?.principal as OidcUser
        val email = principal.email

        if (!checkIfUserExists(email)) {
            registerUser(
                UserRegisterWithGoogleDto(
                    email = email,
                    username = principal.fullName,
                    googleId = principal.idToken.subject
                )
            )
        }

        savedRequestAwareAuthenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication)
    }

    private fun checkIfUserExists(email: String): Boolean {

        val url = "http://localhost:8081/users/$email"

        return try {
            restTemplate.getForEntity(url, Void::class.java)
            true
        }catch (e: Exception) {
            false
        }

    }

    private fun registerUser(dto: UserRegisterWithGoogleDto) {

        val url = "http://localhost:8081/users/register/google"

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val request = HttpEntity(dto, headers)

        restTemplate.postForObject(url, request, Void::class.java)
    }

}