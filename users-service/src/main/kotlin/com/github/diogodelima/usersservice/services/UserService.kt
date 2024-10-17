package com.github.diogodelima.usersservice.services

import com.github.diogodelima.usersservice.domain.User
import com.github.diogodelima.usersservice.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(

    private val userRepository: UserRepository

) {

    fun getByUsername(username: String) = userRepository.findByUsername(username)

    fun getByEmail(email: String) = userRepository.findByEmail(email)

    fun getByUsernameOrEmail(param: String) = userRepository.findByUsername(param) ?: userRepository.findByEmail(param)

    fun save(user: User) = userRepository.save(user)

}