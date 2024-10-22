package com.github.diogodelima.productsservice.services

import com.github.diogodelima.productsservice.domain.Product
import com.github.diogodelima.productsservice.repositories.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(

    private val productRepository: ProductRepository

) {

    fun save(product: Product): Product = productRepository.save(product)

    fun delete(id: Int) = productRepository.deleteById(id)

    fun getById(id: Int): Product? = productRepository.findById(id).orElse(null)

}