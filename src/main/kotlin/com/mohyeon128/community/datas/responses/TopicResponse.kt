package com.mohyeon128.community.datas.responses

import com.mohyeon128.community.datas.tables.Topic

data class TopicResponse(val id: Long, val name: String, val description: String, val engId: String) {
    constructor(topic: Topic) : this(topic.topicId, topic.title, topic.description, topic.topicEngId) {

    }
}