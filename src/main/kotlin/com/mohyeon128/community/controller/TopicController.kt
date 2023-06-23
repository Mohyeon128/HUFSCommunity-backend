package com.mohyeon128.community.controller

import com.mohyeon128.community.datas.responses.TopicResponse
import com.mohyeon128.community.repositories.TopicJpaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topics")
class TopicController(val topicJpaRepository: TopicJpaRepository) {
    @GetMapping()
    fun getTopics(): ResponseEntity<List<TopicResponse>> {
        return ResponseEntity.ok(topicJpaRepository.findAll().map {
            TopicResponse(it)
        })
    }
}