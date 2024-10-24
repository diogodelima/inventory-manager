package com.github.diogodelima.productsservice.controller

import com.github.diogodelima.productsservice.domain.Product
import com.github.diogodelima.productsservice.dto.ApiResponseDto
import com.github.diogodelima.productsservice.dto.ProductCreateDto
import com.github.diogodelima.productsservice.dto.ProductDto
import com.github.diogodelima.productsservice.dto.ProductPageDto
import com.github.diogodelima.productsservice.exceptions.ProductNotFoundException
import com.github.diogodelima.productsservice.services.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductApiController(

    private val productService: ProductService

) {

    @PostMapping
    fun create(@RequestBody @Valid dto: ProductCreateDto): ResponseEntity<ApiResponseDto<ProductDto>> {

        val product = productService.save(
            Product(
                name = dto.name,
                description = dto.description,
                price = dto.price
            )
        )

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                ApiResponseDto(message = "Product created successfully", data = product.toDto())
            )

    }

    @PutMapping
    fun update(@RequestBody @Valid dto: ProductCreateDto): ResponseEntity<ApiResponseDto<ProductDto>> {

        if (dto.id <= 0)
            return ResponseEntity.badRequest().body(
                ApiResponseDto(message = "Id should be grater than 0")
            )

        val product = productService.save(
            Product(
                id = dto.id,
                name = dto.name,
                description = dto.description,
                price = dto.price
            )
        )

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                ApiResponseDto(message = "Product created or updated successfully", data = product.toDto())
            )

    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<ApiResponseDto<Any>> {
        productService.delete(id)
        return ResponseEntity
            .ok(ApiResponseDto(message = "Product deleted successfully"))
    }

    @GetMapping("/page/{page}")
    fun list(
        @PathVariable page: Int,
        @RequestParam(required = false) sort: Product.Sort = Product.Sort.LOWEST_PRICE
    ): ResponseEntity<ApiResponseDto<ProductPageDto>> {

        val pageable = productService.getAll(page - 1, sort)

        return ResponseEntity
            .ok(
                ApiResponseDto(
                    message = "Page retrieved successfully",
                    data = ProductPageDto(
                        currentPage = pageable.number + 1,
                        totalPages = pageable.totalPages,
                        pageSize = pageable.size,
                        products = pageable.toList().map { it.toDto() }
                    )
                )
            )

    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<ApiResponseDto<ProductDto>> {

        val product = productService.getById(id) ?: throw ProductNotFoundException()

        return ResponseEntity
            .ok(
                ApiResponseDto(message = "Product retrieved successfully", data = product.toDto())
            )

    }

}