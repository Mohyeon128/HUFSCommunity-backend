package com.mohyeon128.community.services

import com.mohyeon128.community.datas.tables.UserTopicSubscription
import com.mohyeon128.community.repositories.TopicJpaRepository
import com.mohyeon128.community.repositories.UserJpaRepository
import com.mohyeon128.community.repositories.UserTopicSubscriptionJpaRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class SubscriptionService(
    private val userRepository: UserJpaRepository,
    private val topicRepository: TopicJpaRepository,
    private val userTopicSubscriptionRepository: UserTopicSubscriptionJpaRepository
) {
    @Transactional
    fun subscribe(userId: Int, topicId: Int) {
        val user = userRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User with ID $userId not found") }
        val topic = topicRepository.findById(topicId)
            .orElseThrow { NoSuchElementException("Topic with ID $topicId not found") }
        val subscription = UserTopicSubscription(0, user = user, topic = topic)
        userTopicSubscriptionRepository.save(subscription)
    }

    @Transactional
    fun unsubscribe(userId: Int, topicId: Int) {
        val user = userRepository.findById(userId)
            .orElseThrow { NoSuchElementException("User with ID $userId not found") }
        val topic = topicRepository.findById(topicId)
            .orElseThrow { NoSuchElementException("Topic with ID $topicId not found") }
        userTopicSubscriptionRepository.deleteByUserAndTopic(user, topic)
    }
}