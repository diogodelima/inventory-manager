package com.github.diogodelima.ordersservice.domain

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
@Table(name = "orders")
data class Order(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val userId: Int,

    @OneToMany(mappedBy = "order", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    val items: List<OrderItem>,

    @CreationTimestamp
    val date: Instant,

    val status: Status = Status.PENDING

) {

    val totalPrice = items.sumOf { it.quantity * it.unitPrice }

    enum class Status {

        PENDING,
        SUCCESS,
        CANCELLED

    }

}