package com.mohyeon128.community.datas.tables

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "user_topic_subscriptions")
data class UserTopicSubscription(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val subscriptionId: Long,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    @ManyToOne
    @JoinColumn(name = "topic_id")
    val topic: Topic,
    val subscribedAt: LocalDateTime = LocalDateTime.now(),
    var active: Boolean = true
)