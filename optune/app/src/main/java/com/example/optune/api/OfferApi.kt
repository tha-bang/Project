package com.example.optune.api

import com.example.optune.data.model.Offer
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OfferApi {
    @GET("/offers")
    suspend fun getOffers(): List<Offer>

    @GET("/offers/{id}")
    suspend fun getOfferById(@Path("id") id: String): Offer

    @POST("/offers")
    suspend fun postOffer(@Body offer: Offer): Offer
}
