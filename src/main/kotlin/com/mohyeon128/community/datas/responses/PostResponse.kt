package com.mohyeon128.community.datas.responses

import com.mohyeon128.community.datas.tables.Post

data class PostResponse(
    val id: Long,
    val title: String,
    val content: String,
    val createdDate: String,
    val modifiedDate: String,
    val user: UserResponse,
    val state : String,
    val topics: List<TopicResponse>
) {
    constructor(post: Post) : this(
        post.postId,
        post.title,
        post.content,
        post.createdAt.toString(),
        post.updatedAt.toString(),
        UserResponse(post.user!!),
        post.state,
        post.topicEntities.map { TopicResponse(it) })
}