package com.github.diogodelima.inventoriesservice.domain

import jakarta.persistence.*

@Entity
@Table(name = "items")
data class Item(

    @EmbeddedId
    val id: ItemId,

    @Column(nullable = false)
    val quantity: Int,

    @ManyToOne
    @MapsId("inventoryId")
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    val inventory: Inventory

)

@Embeddable
data class ItemId(
    val productId: Int,
    val inventoryId: Int
)