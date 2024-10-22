package com.github.diogodelima.usersservice.controller

import com.github.diogodelima.usersservice.services.UserService
import org.springframework.test.web.servlet.MockMvc

class UserControllerTest {

    private lateinit var mockMvc: MockMvc
    private lateinit var userService: UserService
    /*private lateinit var passwordEncoder: PasswordEncoder

    @BeforeEach
    fun setup() {
        userService = Mockito.mock(UserService::class.java)
        passwordEncoder = Mockito.mock(PasswordEncoder::class.java)

        val userController = UserController(userService, passwordEncoder)

        mockMvc = MockMvcBuilders
            .standaloneSetup(userController)
            .setControllerAdvice(ExceptionHandler())
            .build()
    }

    @Test
    fun `get user by username or email that doesnt exists returns 404`() {

        val username = "username_that_doesn't_exists"

        mockMvc.get("/users/$username").andExpect {
            status { isNotFound() }
            content {
                contentType(MediaType.APPLICATION_JSON)
            }
        }

    }*/

}