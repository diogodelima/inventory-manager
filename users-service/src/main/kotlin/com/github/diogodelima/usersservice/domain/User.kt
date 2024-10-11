package com.github.diogodelima.usersservice.domain

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false, unique = true)
    val username: String,

    @Column(unique = true)
    val googleId: String? = null,

    val password: String? = null,

) {

    init {
        require(googleId != null || password != null) { "Password or Google ID must be set" }
    }

}
