package com.example.optune.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Application(
    val id: String,
    val userId: String,
    val offerId: String,
    val status: String = "Pending",
    val timestamp: Long
)
