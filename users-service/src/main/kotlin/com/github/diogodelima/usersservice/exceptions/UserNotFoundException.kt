package com.github.diogodelima.usersservice.exceptions

class UserNotFoundException(

    override val message: String? = "User not found."

) : Exception(message)