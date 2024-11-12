package com.github.diogodelima.inventoriesservice.exceptions

class InsufficientStockException(

    override val message: String? = "Insufficient stock for this product"

): RuntimeException(message)