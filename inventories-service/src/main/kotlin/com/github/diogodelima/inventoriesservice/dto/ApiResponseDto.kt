package com.github.diogodelima.inventoriesservice.dto

data class ApiResponseDto<T>(

    val message: String?,
    val data: T? = null

)