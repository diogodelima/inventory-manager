package com.github.diogodelima.inventoriesservice.services

import com.github.diogodelima.inventoriesservice.domain.Item
import com.github.diogodelima.inventoriesservice.exceptions.InsufficientStockException
import com.github.diogodelima.inventoriesservice.exceptions.InventoryNotFoundException
import com.github.diogodelima.inventoriesservice.repository.InventoryRepository
import com.github.diogodelima.inventoriesservice.repository.ItemRepository
import org.springframework.stereotype.Service

@Service
class InventoryService(

    private val inventoryRepository: InventoryRepository,
    private val itemRepository: ItemRepository

) {

    fun removeFromStock(inventoryId: Int, productId: Int, quantity: Int) {

        if (quantity <= 0)
            throw InsufficientStockException()

        val inventory = inventoryRepository.findById(inventoryId).orElseThrow { InventoryNotFoundException() }

        if (!inventory.isInStock(productId, quantity))
            throw InsufficientStockException()

        val item = inventory.getItem(productId)

        itemRepository.save(
            Item(
                productId = productId,
                quantity = item.quantity - quantity,
                inventory = inventory
            )
        )

    }

    fun addToStock(inventoryId: Int, productId: Int, quantity: Int) {

        if (quantity <= 0)
            throw InsufficientStockException()

        val inventory = inventoryRepository.findById(inventoryId).orElseThrow { InventoryNotFoundException() }
        val finalQuantity: Int = inventory.getItemQuantity(productId) + quantity

        itemRepository.save(
            Item(
                productId = productId,
                quantity = finalQuantity,
                inventory = inventory
            )
        )

    }

}