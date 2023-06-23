package com.mohyeon128.community.datas.tables

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "posts")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val postId: Long = 0,

    @Column(nullable = false)
    val title: String = "",

    @Column(nullable = false)
    val state: String = "",

    @Column(nullable = false, columnDefinition = "TEXT")
    val content: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    val user: User? = null,

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "post_topic",
        joinColumns = [JoinColumn(name = "post_id")],
        inverseJoinColumns = [JoinColumn(name = "topic_id")]
    )
    val topicEntities: List<Topic> = mutableListOf(),

    @Column(nullable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = true)
    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null
)