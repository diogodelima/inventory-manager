package com.github.diogodelima.usersservice.dto

import jakarta.validation.constraints.NotBlank

data class UserRegisterDto(

    @field:NotBlank
    val email: String,

    @field:NotBlank
    val username: String,

    @field:NotBlank
    val password: String

)