package com.mohyeon128.community.datas.tables

import jakarta.persistence.*

@Entity
@Table(name = "topics")
data class Topic(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val topicId: Long = 0,

    @Column(nullable = false, unique = true)
    val topicEngId: String = "",

    @Column(nullable = false)
    val title: String = "",

    val description: String = "",

    @ManyToMany(mappedBy = "topicEntities")
    val postEntities: List<Post> = mutableListOf(),

    @OneToMany(mappedBy = "topic", cascade = [CascadeType.ALL], orphanRemoval = true)
    var subscriptions: List<UserTopicSubscription> = mutableListOf()
)