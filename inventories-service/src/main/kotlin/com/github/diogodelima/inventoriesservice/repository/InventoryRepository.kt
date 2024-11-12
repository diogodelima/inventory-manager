package com.github.diogodelima.inventoriesservice.repository

import com.github.diogodelima.inventoriesservice.domain.Inventory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InventoryRepository: JpaRepository<Inventory, Int>