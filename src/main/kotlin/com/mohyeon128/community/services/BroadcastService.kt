package com.mohyeon128.community.services

import com.mohyeon128.community.datas.tables.User
import com.mohyeon128.community.libs.EmailTemplate
import com.mohyeon128.community.repositories.PostJpaRepository
import com.mohyeon128.community.repositories.TopicJpaRepository
import com.mohyeon128.community.repositories.UserTopicSubscriptionJpaRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class BroadcastService(
    val mailService: MailService,
    val postJpaRepository: PostJpaRepository,
    val subscriptionRepository: UserTopicSubscriptionJpaRepository
) {

    fun broadcast(postId: Long) {
        // Get the post by its ID
        val post = postJpaRepository.findByPostId(postId)

        // Get the topics of the post
        val topics = post.topicEntities

        // Find the subscriptions for the topics
        val subscriptions = subscriptionRepository.findByTopicIn(topics)

        // Extract the users from the subscriptions, removing duplicates
        subscriptions.map { it.user }.distinct().forEach {
            println(it.email)
            mailService.send("COALARM <coalarm@hufsdevelopers.org>", it.email, post.title, EmailTemplate.generate(post.title, post.content.replace("\n", "<br/>").replace(" ", "&nbsp;")))
        }
    }
}