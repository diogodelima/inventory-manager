package com.github.diogodelima.inventoriesservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InventoriesServiceApplication

fun main(args: Array<String>) {
    runApplication<InventoriesServiceApplication>(*args)
}
