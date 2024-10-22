package com.github.diogodelima.productsservice.repositories

import com.github.diogodelima.productsservice.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Int>