package com.github.diogodelima.ordersservice.domain

import jakarta.persistence.*

@Entity
@Table(name = "order_items")
data class OrderItem(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val productId: Int,

    val unitPrice: Double,

    val quantity: Int,

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    val order: Order

)