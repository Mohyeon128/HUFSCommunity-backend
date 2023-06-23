package com.mohyeon128.community.repositories

import com.mohyeon128.community.datas.tables.Topic
import com.mohyeon128.community.datas.tables.User
import com.mohyeon128.community.datas.tables.UserTopicSubscription
import org.springframework.data.jpa.repository.JpaRepository

interface UserTopicSubscriptionJpaRepository : JpaRepository<UserTopicSubscription, Int> {
    fun deleteByUserAndTopic(user: User, topic: Topic)

    fun findByUserId(userId: Long): List<UserTopicSubscription>

    fun findByTopicIn(topics: List<Topic>): List<UserTopicSubscription>
}