package com.github.diogodelima.productsservice.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(

    val id: Int,

    val name: String,

    val description: String,

    val date: Long,

    val price: Double

)