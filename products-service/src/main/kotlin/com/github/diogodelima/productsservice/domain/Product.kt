package com.github.diogodelima.productsservice.domain

import jakarta.persistence.*
import org.springframework.data.domain.Sort.Direction
import java.time.Instant

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
    val price: Double = 0.0,

    @Column(nullable = false)
    val date: Instant = Instant.now()

) {

    enum class Sort(

        val direction: Direction,
        val field: String

    ) {

        LATEST(Direction.DESC, "date"),
        OLDEST(Direction.ASC, "date"),
        LOWEST_PRICE(Direction.ASC, "price"),
        HIGHEST_PRICE(Direction.DESC, "price")

    }

}