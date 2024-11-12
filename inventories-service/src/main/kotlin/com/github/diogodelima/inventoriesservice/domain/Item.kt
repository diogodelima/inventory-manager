package com.github.diogodelima.inventoriesservice.domain

import jakarta.persistence.*

@Entity
@Table(name = "items")
data class Item(

    @Id
    val productId: Int,

    val quantity: Int,

    @Id
    @ManyToOne
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    val inventory: Inventory

)