package com.github.diogodelima.usersservice.controller

import com.github.diogodelima.usersservice.dto.ApiResponseDto
import com.github.diogodelima.usersservice.dto.UserDto
import com.github.diogodelima.usersservice.exceptions.UserNotFoundException
import com.github.diogodelima.usersservice.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(

    private val userService: UserService

) {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello from Users Service!"
    }

    @GetMapping("/{param}")
    fun getByEmailOrUsername(@PathVariable param: String): ResponseEntity<ApiResponseDto<UserDto>> {

        val user = userService.getByUsernameOrEmail(param) ?: throw UserNotFoundException()

        return ResponseEntity
            .ok()
            .body(
                ApiResponseDto(
                    message = "User retrieved successfully.",
                    data = user.toDto()
                )
            )

    }

}