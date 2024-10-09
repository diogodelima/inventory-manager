package com.github.diogodelima.authorizationserver.domain

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @get:JvmName("username")
    @Column(unique = true, nullable = false)
    val username: String = "",

    @get:JvmName("password")
    @Column(nullable = false)
    val password: String = ""

): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

}