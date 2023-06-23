package com.mohyeon128.community.datas.responses

import com.mohyeon128.community.datas.tables.User

data class UserResponse(val name: String, val email: String) {

    constructor(user: User) : this(user.name, user.email) {

    }
}