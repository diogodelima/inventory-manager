package com.github.diogodelima.inventoriesservice.exceptions

class InventoryNotFoundException(

    override val message: String? = "Inventory not found"

): RuntimeException(message)