package com.mohyeon128.community.configs

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream
import java.io.IOException


@Configuration
class FirebaseConfig {
    @PostConstruct
    fun initialize() {
        try {
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build()

            FirebaseApp.initializeApp(options)
            println("Firebase SDK initialized.")
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}