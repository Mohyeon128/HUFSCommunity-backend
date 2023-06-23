package com.mohyeon128.community.repositories

import com.mohyeon128.community.datas.tables.Post
import com.mohyeon128.community.datas.tables.Topic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostJpaRepository : JpaRepository<Post, Int> {
}