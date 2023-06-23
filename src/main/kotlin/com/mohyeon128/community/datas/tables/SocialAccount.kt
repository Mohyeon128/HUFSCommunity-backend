package com.mohyeon128.community.datas.tables

import jakarta.persistence.*

@Table(name = "social_accounts")
@Entity
data class SocialAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "provider_name")
    val providerName: String,
    @Column(name = "provider_id")
    val providerId: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null
) {

}