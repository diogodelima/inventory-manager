package com.github.diogodelima.inventoriesservice.domain

import jakarta.persistence.*

@Entity
@Table(name = "inventories")
data class Inventory(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false)
    val name: String,

    @OneToMany(mappedBy = "inventory", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    val items: List<Item> = emptyList()

) {

    fun isInStock(productId: Int, amount: Int) = this.items.any { it.productId == productId && it.quantity >= amount }

    fun getItem(productId: Int) = this.items.first { it.productId == productId }

    fun getItemQuantity(productId: Int): Int = this.items.firstOrNull { it.productId == productId }?.quantity ?: 0

}