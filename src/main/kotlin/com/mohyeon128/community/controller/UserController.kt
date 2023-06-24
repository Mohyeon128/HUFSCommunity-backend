package com.mohyeon128.community.controller

import com.mohyeon128.community.datas.requestforms.UserRegistForm
import com.mohyeon128.community.datas.responses.PostResponse
import com.mohyeon128.community.datas.responses.TopicResponse
import com.mohyeon128.community.datas.responses.UserResponse
import com.mohyeon128.community.datas.tables.Post
import com.mohyeon128.community.datas.tables.Topic
import com.mohyeon128.community.datas.tables.User
import com.mohyeon128.community.services.AuthService
import com.mohyeon128.community.services.BroadcastService
import com.mohyeon128.community.services.SubscriptionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    val authService: AuthService,
    val subscriptionService: SubscriptionService,
    val broadcastService: BroadcastService
) {
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

    @GetMapping("/{userId}/subscriptions")
    fun getUserSubscriptions(@PathVariable userId: Long): ResponseEntity<List<TopicResponse>> {
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(userId).map {
            TopicResponse(it)
        })
    }

    @GetMapping("/{userId}/timeline")
    fun getUserTimeline(@PathVariable userId: Long): ResponseEntity<List<PostResponse>> {
        val posts = broadcastService.getUserTimeline(userId)
        return ResponseEntity.ok(posts.map { PostResponse(it) })
    }
}