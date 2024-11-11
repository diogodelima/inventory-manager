package com.github.diogodelima.productsservice.exceptions

class ProductAlreadyExistsException(

    override val message: String? = "Product already exists"

): RuntimeException(message)