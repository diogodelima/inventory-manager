package com.github.diogodelima.inventoriesservice.repository

import com.github.diogodelima.inventoriesservice.domain.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository: JpaRepository<Item, Int>