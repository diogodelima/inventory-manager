package com.github.diogodelima.productsservice.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ProductCreateDto(

    val id: Int = 0,

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val description: String,

    @field:NotNull
    val price: Double

)