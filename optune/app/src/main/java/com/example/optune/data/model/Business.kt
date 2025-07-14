package com.example.optune.data.model

import com.google.firebase.firestore.DocumentId

data class Business(
    @DocumentId
    val id: String = "",
    val userId: String = "",
    val companyName: String = "",
    val contactPerson: String = "",
    val industry: String = "",
    val email: String = "",
    val phone: String = ""
)