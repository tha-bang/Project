package com.example.optune.network // Adjust package name if you placed it elsewhere

import com.example.optune.data.model.Offer
import retrofit2.http.GET
// import retrofit2.http.POST // Example if you had a POST method
// import retrofit2.http.Body // Example for POST

interface EofferApiService {

    @GET("offers") // <-- IMPORTANT: Replace "offers" with your actual API endpoint path for fetching all offers
    suspend fun getOffers(): List<Offer>

    // Example for fetching a single offer by ID (if you need it)
    // @GET("offers/{id}")
    // suspend fun getOfferById(@Path("id") offerId: String): Offer

    // Example for posting an offer (if you need it)
    // @POST("offers")
    // suspend fun postOffer(@Body offer: Offer): Offer // Or whatever your API returns on post

    // Add other API methods here as needed (e.g., for user authentication, posting data, etc.)
}
