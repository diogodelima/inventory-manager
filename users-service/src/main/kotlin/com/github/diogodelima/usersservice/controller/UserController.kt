package com.github.diogodelima.usersservice.controller

import com.github.diogodelima.usersservice.domain.User
import com.github.diogodelima.usersservice.dto.ApiResponseDto
import com.github.diogodelima.usersservice.dto.UserDto
import com.github.diogodelima.usersservice.dto.UserRegisterDto
import com.github.diogodelima.usersservice.exceptions.UserAlreadyExistsException
import com.github.diogodelima.usersservice.exceptions.UserNotFoundException
import com.github.diogodelima.usersservice.services.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(

    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder

) {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello from Users Service!"
    }

    @PostMapping("/register")
    fun register(@RequestBody @Valid dto: UserRegisterDto): ResponseEntity<ApiResponseDto<UserDto>> {

        if (userService.getByEmail(dto.email) != null && userService.getByUsername(dto.username) != null)
            throw UserAlreadyExistsException()

        val user = userService.save(
            User(
                email = dto.email,
                username = dto.username,
                password = passwordEncoder.encode(dto.password)
            )
        )

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                ApiResponseDto(
                    message = "User created successfully.",
                    response = UserDto(user)
                )
            )

    }

    @GetMapping("/{param}")
    fun getByEmailOrUsername(@PathVariable param: String): ResponseEntity<ApiResponseDto<UserDto>> {

        val user = userService.getByUsernameOrEmail(param) ?: throw UserNotFoundException()

        return ResponseEntity
            .ok()
            .body(
                ApiResponseDto(
                    message = "User retrieved successfully.",
                    response = UserDto(user)
                )
            )

    }

}