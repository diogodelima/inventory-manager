package com.github.diogodelima.productsservice.controller

import com.github.diogodelima.productsservice.domain.Product
import com.github.diogodelima.productsservice.dto.ApiResponseDto
import com.github.diogodelima.productsservice.dto.ProductCreateDto
import com.github.diogodelima.productsservice.dto.ProductDto
import com.github.diogodelima.productsservice.dto.ProductPageDto
import com.github.diogodelima.productsservice.services.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(

    private val productService: ProductService

) {

    @GetMapping("/secure")
    fun secure(): String {
        return "secure"
    }

    @GetMapping("/free")
    fun free(): String {
        return "free"
    }

    @PostMapping
    fun create(@RequestBody @Valid dto: ProductCreateDto): ResponseEntity<ApiResponseDto<ProductDto>> {

        val product = productService.create(
            name = dto.name,
            description = dto.description,
            price = dto.price
        )

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                ApiResponseDto(message = "Product created successfully", data = product.toDto())
            )

    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody @Valid dto: ProductCreateDto): ResponseEntity<ApiResponseDto<ProductDto>> {

        val product = productService.update(
            id = id,
            name = dto.name,
            description = dto.description,
            price = dto.price
        )

        return ResponseEntity
            .ok(
                ApiResponseDto(message = "Product updated successfully", data = product.toDto())
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

        val product = productService.getById(id)

        return ResponseEntity
            .ok(
                ApiResponseDto(message = "Product retrieved successfully", data = product.toDto())
            )

    }

}

private fun Product.toDto() = ProductDto(
    id = this.id,
    name = this.name,
    description = this.description,
    price = this.price,
    date = this.date.toEpochMilli()
)