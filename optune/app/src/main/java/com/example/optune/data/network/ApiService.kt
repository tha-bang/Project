package com.example.optune.data.network

import com.example.optune.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("api/users")
    suspend fun createUser(@Body user: User): Response<User>

    @GET("api/users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): Response<User>

    @GET("/users")
    suspend fun getUsers(): List<User>
}
