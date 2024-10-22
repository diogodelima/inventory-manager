package com.github.diogodelima.usersservice.controller

import com.github.diogodelima.usersservice.dto.UserDto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/users")
class UserController {

    @GetMapping("/register")
    fun register(model: Model): String {
        model.addAttribute("dto", UserDto())
        return "register"
    }

}