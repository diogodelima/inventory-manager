package com.github.diogodelima.usersservice.controller

import com.github.diogodelima.usersservice.exceptions.handler.ExceptionHandler
import com.github.diogodelima.usersservice.services.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class UserApiControllerTest {

    private lateinit var mockMvc: MockMvc
    private lateinit var userService: UserService

    @BeforeEach
    fun setup() {
        userService = Mockito.mock(UserService::class.java)

        val userController = UserApiController(userService)

        mockMvc = MockMvcBuilders
            .standaloneSetup(userController)
            .setControllerAdvice(ExceptionHandler())
            .build()
    }

    @Test
    fun `get user by username or email that doesn't exists returns 404`() {

        val username = "username_that_doesn't_exists"

        mockMvc.get("/api/users/$username").andExpect {
            status { isNotFound() }
            content {
                contentType(MediaType.APPLICATION_JSON)
            }
        }

    }

}