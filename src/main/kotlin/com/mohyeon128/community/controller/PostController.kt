package com.mohyeon128.community.controller

import com.mohyeon128.community.datas.requestforms.PostRegistForm
import com.mohyeon128.community.datas.responses.PostResponse
import com.mohyeon128.community.datas.tables.Post
import com.mohyeon128.community.datas.tables.Topic
import com.mohyeon128.community.repositories.PostJpaRepository
import com.mohyeon128.community.repositories.TopicJpaRepository
import com.mohyeon128.community.repositories.UserJpaRepository
import com.mohyeon128.community.services.BroadcastService
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(
    val userJpaRepository: UserJpaRepository,
    val postJpaRepository: PostJpaRepository,
    val topicJpaRepository: TopicJpaRepository,
    val broadcastService: BroadcastService
) {
    @GetMapping("/{postId}")
    fun getPostById(@PathVariable postId: Int): ResponseEntity<PostResponse> {
        return ResponseEntity.ok(PostResponse(postJpaRepository.findById(postId).get()))
    }

    @GetMapping("/{postId}/broadcast")
    fun broadcastPost(@PathVariable postId: Long): ResponseEntity<Boolean> {
        broadcastService.broadcast(postId)
        return ResponseEntity.ok(true)
    }

    @GetMapping
    fun getPosts(@RequestParam topicId: Long?): ResponseEntity<List<PostResponse>> {
        if (topicId == null)
            return ResponseEntity.ok(postJpaRepository.findAll().map {
                PostResponse(it)
            })
        else {
            return ResponseEntity.ok(topicJpaRepository.findByTopicId(topicId).postEntities.map {
                PostResponse(it)
            })
        }
    }

    @PostMapping
    fun postPost(@RequestBody postRegistForm: PostRegistForm): ResponseEntity<PostResponse> {
        val user = userJpaRepository.findById(1).get()
        val topics = mutableListOf<Topic>()
        postRegistForm.topicIds.forEach {
            topics.add(topicJpaRepository.findByTopicId(it))
        }

        val post = Post(0, postRegistForm.title, "pending", postRegistForm.content, user, topics)
        return ResponseEntity.ok(PostResponse(postJpaRepository.save(post)))
    }
}