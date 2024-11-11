package com.github.diogodelima.productsservice.services

import com.github.diogodelima.productsservice.domain.Product
import com.github.diogodelima.productsservice.exceptions.ProductAlreadyExistsException
import com.github.diogodelima.productsservice.exceptions.ProductIdException
import com.github.diogodelima.productsservice.exceptions.ProductNotFoundException
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

    fun create(name: String, description: String, price: Double): Product =
        productRepository.save(
            Product(
                name = name,
                description = description,
                price = price
            )
        )

    fun update(id: Int, name: String, description: String, price: Double): Product {

        if (id <= 0)
            throw ProductIdException()

        if (productRepository.findById(id).isPresent)
            throw ProductAlreadyExistsException()

        return productRepository.save(
            Product(
                id = id,
                name = name,
                description = description,
                price = price
            )
        )
    }

    fun delete(id: Int) = productRepository.deleteById(id)

    fun getById(id: Int): Product = productRepository.findById(id).orElseThrow { ProductNotFoundException() }

    fun getAll(page: Int, productSort: Product.Sort): Page<Product> {

        val sort = Sort.by(productSort.direction, productSort.field)
        val pageable = PageRequest.of(page, PAGE_SIZE, sort)

        return productRepository.findAll(pageable)
    }

}