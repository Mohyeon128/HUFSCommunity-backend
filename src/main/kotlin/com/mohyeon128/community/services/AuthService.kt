package com.mohyeon128.community.services

import com.mohyeon128.community.datas.tables.SocialAccount
import com.mohyeon128.community.datas.tables.User
import com.mohyeon128.community.repositories.UserJpaRepository
import org.springframework.stereotype.Service

@Service
class AuthService(val userJpaRepository: UserJpaRepository, val mailService: MailService) {
    fun regist(name: String, email: String, firebaseUid: String) {
        val user = User(
            0,
            name,
            email
        )

        user.socialAccountEntities.add(SocialAccount(0, "firebaseauth", firebaseUid, user))

        mailService.send("mohyeon128@hufsdevelopers.org", email, "가입을 축하합니다.", "네!")

        userJpaRepository.save(user)
    }

    fun get(id: Int): User {
        return userJpaRepository.findById(id).get()
    }
}