package com.example.optune.data.model


import kotlinx.serialization.Serializable

@Serializable

data class Offer(
    val id: String,
    val title: String,
    val description: String,
    val businessName: String
)
