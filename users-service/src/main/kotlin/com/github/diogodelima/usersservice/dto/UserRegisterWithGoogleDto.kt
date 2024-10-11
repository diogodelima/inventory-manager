package com.github.diogodelima.usersservice.dto

import jakarta.validation.constraints.NotBlank

data class UserRegisterWithGoogleDto(

    @field:NotBlank
    val email: String,

    @field:NotBlank
    val username: String,

    @field:NotBlank
    val googleId: String

)