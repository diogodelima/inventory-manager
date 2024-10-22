package com.github.diogodelima.usersservice.dto

import com.github.diogodelima.usersservice.domain.User

data class UserDto(

    val id: Int = 0,

    val email: String = "",

    val username: String = ""

)