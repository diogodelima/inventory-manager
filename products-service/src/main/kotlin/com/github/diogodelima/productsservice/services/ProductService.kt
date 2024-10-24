package com.github.diogodelima.productsservice.services

import com.github.diogodelima.productsservice.domain.Product
import com.github.diogodelima.productsservice.repositories.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

private const val PAGE_SIZE = 10

@Service
class ProductService(

    private val productRepository: ProductRepository

) {

    fun save(product: Product): Product = productRepository.save(product)

    fun delete(id: Int) = productRepository.deleteById(id)

    fun getById(id: Int): Product? = productRepository.findById(id).orElse(null)

    fun getAll(page: Int, productSort: Product.Sort): Page<Product> {

        val sort = Sort.by(productSort.direction, productSort.field)
        val pageable = PageRequest.of(page, PAGE_SIZE, sort)

        return productRepository.findAll(pageable)
    }

}