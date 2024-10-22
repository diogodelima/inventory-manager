package com.github.diogodelima.productsservice.domain

import com.github.diogodelima.productsservice.dto.ProductDto
import jakarta.persistence.*

@Entity
@Table(name = "products")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false)
    val name: String = "",

    @Column(nullable = false)
    val description: String = "",

    @Column(nullable = false)
    val price: Double = 0.0

) {

    fun toDto() = ProductDto(id, name, description, price)

}