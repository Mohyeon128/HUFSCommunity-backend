package com.mohyeon128.community.datas.tables

import jakarta.persistence.*

@Entity
@Table(name = "posts")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val postId: Long = 0,

    @Column(nullable = false)
    val title: String = "",

    @Column(nullable = false, columnDefinition = "TEXT")
    val content: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User? = null,

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "post_topic",
        joinColumns = [JoinColumn(name = "post_id")],
        inverseJoinColumns = [JoinColumn(name = "topic_id")]
    )
    val topics: List<Topic> = mutableListOf()
)