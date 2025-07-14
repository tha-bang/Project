package com.example.optune.api

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory // Ensure this import is present

object RetrofitInstance {
    val api: OfferApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType())) // Added ignoreUnknownKeys as good practice
            .build()
            .create(OfferApi::class.java)
    }
}