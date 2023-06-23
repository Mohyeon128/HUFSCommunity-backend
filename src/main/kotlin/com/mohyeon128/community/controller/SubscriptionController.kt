package com.mohyeon128.community.controller

import com.mohyeon128.community.datas.requestforms.SubscriptionRequest
import com.mohyeon128.community.services.SubscriptionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/subscriptions")
class SubscriptionController(private val subscriptionService: SubscriptionService) {

    @PostMapping
    fun subscribe(@RequestBody subscriptionRequest: SubscriptionRequest) {
        subscriptionService.subscribe(subscriptionRequest.userId, subscriptionRequest.topicId)
    }

    @DeleteMapping
    fun unsubscribe(@RequestBody subscriptionRequest: SubscriptionRequest) {
        subscriptionService.unsubscribe(subscriptionRequest.userId, subscriptionRequest.topicId)
    }
}