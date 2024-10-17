package com.github.diogodelima.usersservice.exceptions.handler

import com.github.diogodelima.usersservice.dto.ApiResponseDto
import com.github.diogodelima.usersservice.exceptions.UserAlreadyExistsException
import com.github.diogodelima.usersservice.exceptions.UserNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(UserNotFoundException::class)
    fun handleNotFound(ex: Exception, request: WebRequest): ResponseEntity<Any>? {
        return handleExceptionInternal(ex, ApiResponseDto<Any>(message = ex.message), HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleConflict(ex: Exception, request: WebRequest): ResponseEntity<Any>? {
        return handleExceptionInternal(ex, ApiResponseDto<Any>(message = ex.message), HttpHeaders(), HttpStatus.CONFLICT, request)
    }

}