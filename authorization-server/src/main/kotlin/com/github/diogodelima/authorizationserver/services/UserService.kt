package com.github.diogodelima.authorizationserver.services

import com.github.diogodelima.authorizationserver.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(

    private val userRepository: UserRepository

): UserDetailsService {

    override fun loadUserByUsername(username: String?) = userRepository.findByUsername(username) ?: throw UsernameNotFoundException(username)

}