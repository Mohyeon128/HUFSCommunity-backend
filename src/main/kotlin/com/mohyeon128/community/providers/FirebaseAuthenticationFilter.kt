package com.mohyeon128.community.providers

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class FirebaseAuthenticationFilter(private val firebaseTokenProvider: FirebaseTokenProvider) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = firebaseTokenProvider.resolveToken(request)

        if (token != null) {
            SecurityContextHolder.getContext().authentication = firebaseTokenProvider.getAuthentication(token)
        }
        filterChain.doFilter(request, response)
    }
}