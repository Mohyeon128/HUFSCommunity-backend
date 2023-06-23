package com.mohyeon128.community.repositories

import com.mohyeon128.community.datas.tables.SocialAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SocialAccountJpaRepository : JpaRepository<SocialAccount, Int> {
    fun findByProviderNameAndProviderId(providerName: String, providerId: String): SocialAccount?
}
