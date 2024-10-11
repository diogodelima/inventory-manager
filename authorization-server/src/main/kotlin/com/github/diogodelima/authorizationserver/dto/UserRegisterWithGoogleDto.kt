package com.github.diogodelima.authorizationserver.dto

data class UserRegisterWithGoogleDto(

    val email: String,

    val username: String,

    val googleId: String

)