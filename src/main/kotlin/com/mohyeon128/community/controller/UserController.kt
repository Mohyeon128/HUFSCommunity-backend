package com.mohyeon128.community.controller

import com.mohyeon128.community.datas.requestforms.UserRegistForm
import com.mohyeon128.community.repositories.UserJpaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(val userJpaRepository: UserJpaRepository) {
    @PostMapping
    fun regist(@RequestParam userRegistForm: UserRegistForm) : ResponseEntity<Boolean> {
        return ResponseEntity.ok(true)
    }
}