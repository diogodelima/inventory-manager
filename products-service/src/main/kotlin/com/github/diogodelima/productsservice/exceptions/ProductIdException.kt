package com.github.diogodelima.productsservice.exceptions

class ProductIdException(

    override val message: String? = "Product Id should be grater than 0"

): RuntimeException(message)