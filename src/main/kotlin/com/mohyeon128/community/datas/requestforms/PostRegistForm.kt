package com.mohyeon128.community.datas.requestforms

import com.mohyeon128.community.datas.responses.TopicResponse
import com.mohyeon128.community.datas.responses.UserResponse

data class PostRegistForm(val title: String, val content: String, val topicIds: List<Long>) {
}