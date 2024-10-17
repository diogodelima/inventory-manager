package com.github.diogodelima.productsservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class HelloController {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello from Products Service!"
    }

}