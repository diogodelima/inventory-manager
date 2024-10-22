package com.github.diogodelima.usersservice.domain

import com.github.diogodelima.usersservice.dto.UserDto
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false, unique = true)
    val username: String,

    val password: String,

) {

    fun toDto() = UserDto(id, email, username)

}
