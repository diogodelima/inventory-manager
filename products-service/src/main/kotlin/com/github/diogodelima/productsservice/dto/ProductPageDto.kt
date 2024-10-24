package com.github.diogodelima.productsservice.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductPageDto(

    @SerialName("current_page")
    val currentPage: Int,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("page_size")
    val pageSize: Int,

    val products: List<ProductDto>

)