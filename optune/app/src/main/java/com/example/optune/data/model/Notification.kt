package com.example.optune.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Notification(
    val id: String,
    val userId: String,
    val message: String,
    val read: Boolean = false,
    val timestamp: Long
)
