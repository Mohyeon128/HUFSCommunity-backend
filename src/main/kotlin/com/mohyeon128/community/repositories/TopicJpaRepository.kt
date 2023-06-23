package com.mohyeon128.community.repositories

import com.mohyeon128.community.datas.tables.Topic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicJpaRepository : JpaRepository<Topic, Int> {
    fun findByTopicId(topicId : Long) : Topic
}