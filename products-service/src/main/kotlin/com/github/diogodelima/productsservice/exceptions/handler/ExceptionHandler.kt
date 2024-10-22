package com.github.diogodelima.productsservice.exceptions.handler

import com.github.diogodelima.productsservice.exceptions.ProductNotFoundException
import com.github.diogodelima.productsservice.dto.ApiResponseDto
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ProductNotFoundException::class)
    fun handleNotFound(ex: Exception, request: WebRequest): ResponseEntity<Any>? {
        return handleExceptionInternal(ex, ApiResponseDto<Any>(message = ex.message), HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }

}