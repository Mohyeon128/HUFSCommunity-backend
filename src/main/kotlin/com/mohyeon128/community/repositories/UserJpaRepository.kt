package com.mohyeon128.community.repositories

import com.mohyeon128.community.datas.tables.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : JpaRepository<User, Int> {
}
