package com.mohyeon128.community.datas.tables

import jakarta.persistence.*

@Table(name = "users")
@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "name")
    val name: String,
    @Column(name = "email")
    val email: String,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var socialAccounts: MutableList<SocialAccount> = mutableListOf()
) {
}