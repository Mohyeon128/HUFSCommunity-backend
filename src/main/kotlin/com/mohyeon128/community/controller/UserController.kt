package com.mohyeon128.community.controller

import com.mohyeon128.community.datas.requestforms.UserRegistForm
import com.mohyeon128.community.datas.responses.UserResponse
import com.mohyeon128.community.datas.tables.User
import com.mohyeon128.community.services.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(val authService: AuthService) {
    @PostMapping
    fun regist(@RequestBody userRegistForm: UserRegistForm): ResponseEntity<Boolean> {
        authService.regist(userRegistForm.name, userRegistForm.email, userRegistForm.firebaseUid)
        return ResponseEntity.ok(true)
    }

    @GetMapping
    fun get(): ResponseEntity<UserResponse> {
        val user: User = authService.get(1)

        return ResponseEntity.ok(UserResponse(user))
    }
}