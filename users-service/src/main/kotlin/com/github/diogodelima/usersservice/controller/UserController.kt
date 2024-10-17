package com.github.diogodelima.usersservice.controller

import com.github.diogodelima.usersservice.domain.User
import com.github.diogodelima.usersservice.dto.UserRegisterDto
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
    fun register(@RequestBody @Valid dto: UserRegisterDto): ResponseEntity<Any> {

        val user = userService.save(
            User(
                email = dto.email,
                username = dto.username,
                password = passwordEncoder.encode(dto.password)
            )
        )

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build()
    }

    @GetMapping("/{param}")
    fun getByEmailOrUsername(@PathVariable param: String): ResponseEntity<Any> {

        val user = userService.getByEmail(param) ?: userService.getByUsername(param) ?: throw UserNotFoundException()

        return ResponseEntity.ok().build()
    }

}