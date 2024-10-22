package com.github.diogodelima.usersservice.dto

data class ApiResponseDto<T>(

    val message: String?,
    val data: T? = null

)