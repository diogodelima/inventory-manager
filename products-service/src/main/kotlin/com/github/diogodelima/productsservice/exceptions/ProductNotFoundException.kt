package com.github.diogodelima.productsservice.exceptions

class ProductNotFoundException(

    override val message: String? = "Product not found"

) : Exception(message)