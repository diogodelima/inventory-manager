package com.github.diogodelima.usersservice.exceptions

class UserAlreadyExistsException(

    override val message: String? = "User already exists"

) : Exception(message)