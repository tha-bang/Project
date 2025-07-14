package com.example.optune.data.model


import kotlinx.serialization.Serializable

@Serializable

data class Offer(
    val id: String,
    val title: String,
    val description: String,
    val businessName: String,
    val location: String,
    val jobType: String,
    val salaryRange: String,
    val experienceLevel: String,
    val opportunityType: String = "Job", // New field for opportunity type
    val date: String = "", // For events, workshops, etc.
    val requirements: String = "", // For scholarships, volunteering
    val duration: String = "" // For volunteering, internships
)