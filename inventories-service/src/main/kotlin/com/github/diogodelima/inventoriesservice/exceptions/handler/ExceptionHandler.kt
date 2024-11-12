package com.github.diogodelima.inventoriesservice.exceptions.handler

import com.github.diogodelima.inventoriesservice.dto.ApiResponseDto
import com.github.diogodelima.inventoriesservice.exceptions.InventoryNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(InventoryNotFoundException::class)
    fun handleNotFound(ex: Exception): ResponseEntity<ApiResponseDto<Any>> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ApiResponseDto(
                    message = ex.message
                )
            )

}