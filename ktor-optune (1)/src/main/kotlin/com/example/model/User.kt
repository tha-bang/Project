package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userId: String,
    val name: String,
    val email: String,
    val signUpMethod: String,
    val skills: List<String>,
    val interests: List<String>,
    val education: String,
    val cv: String
)