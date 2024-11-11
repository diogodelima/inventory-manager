package com.github.diogodelima.productsservice.exceptions.handler

import com.github.diogodelima.productsservice.exceptions.ProductNotFoundException
import com.github.diogodelima.productsservice.dto.ApiResponseDto
import com.github.diogodelima.productsservice.exceptions.ProductIdException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ProductNotFoundException::class)
    fun handleNotFound(ex: Exception): ResponseEntity<ApiResponseDto<Any>> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ApiResponseDto(
                    message = ex.message
                )
            )

    @ExceptionHandler(ProductIdException::class)
    fun handleBadRequest(ex: Exception): ResponseEntity<ApiResponseDto<Any>> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ApiResponseDto(
                    message = ex.message
                )
            )

}