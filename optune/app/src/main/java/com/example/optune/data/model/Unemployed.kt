package com.example.optune.data.model

import com.google.firebase.firestore.DocumentId

data class Unemployed(
    @DocumentId
    val id: String = "",
    val userId: String = "",
    val firstname: String = "",
    val lastname: String = "",
    val username: String = "",
    val identificationNumber: String = "",
    val birthDate: String = "",
    val gender: String = ""
)