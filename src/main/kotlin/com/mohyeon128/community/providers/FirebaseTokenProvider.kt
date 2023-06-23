package com.mohyeon128.community.providers

import com.google.firebase.auth.FirebaseAuth
import com.mohyeon128.community.repositories.SocialAccountJpaRepository
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component


@Component
class FirebaseTokenProvider(val socialAccountJpaRepository: SocialAccountJpaRepository) {
    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else null
    }

    fun getFirebaseUidFromToken(token: String?): String {
        return try {
            val decodedToken = FirebaseAuth.getInstance().verifyIdToken(token)
            val uid = decodedToken.uid
            uid
        } catch (e: Exception) {
            ""
        }
    }

    fun getAuthentication(token: String?): Authentication? {
        val uid = getFirebaseUidFromToken(token)
        val userDetails: UserDetails = socialAccountJpaRepository.findByProviderNameAndProviderId("firebaseauth", "sd")?.user!!
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

}