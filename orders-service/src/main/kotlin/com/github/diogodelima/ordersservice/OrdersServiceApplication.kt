package com.github.diogodelima.ordersservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OrdersServiceApplication

fun main(args: Array<String>) {
	runApplication<OrdersServiceApplication>(*args)
}
