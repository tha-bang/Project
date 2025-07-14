package com.example.optune.di

// Your existing imports
import com.example.optune.data.remote.UserDataSource
import com.example.optune.data.repository.UserRepository

// Imports needed for Retrofit and EofferApiService
import com.example.optune.data.network.EofferApiService // <--- Make sure this path is correct
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

// Dagger Hilt imports
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // --- Your Existing Providers ---
    @Provides
    @Singleton
    fun provideUserDataSource(): UserDataSource {
        return UserDataSource()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDataSource: UserDataSource): UserRepository {
        return UserRepository(userDataSource)
    }

    // --- New Network Providers ---

    // You might want to make this configurable or store it in buildConfig
    // TODO: Replace with your actual API base URL
    private const val BASE_URL = "http://10.0.2.2:8080/" // Standard for Android emulator to access localhost

    @Provides
    @Singleton
    fun provideJson(): Json = Json { // Provides kotlinx.serialization.Json instance
        ignoreUnknownKeys = true // Good practice: ignore JSON keys not defined in your data class
        isLenient = true         // Good practice: be lenient with malformed JSON, if appropriate
    }

    @Provides
    @Singleton
    fun provideRetrofit(json: Json): Retrofit { // Inject the Json instance
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Use asConverterFactory for kotlinx.serialization
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            // OR if you were using Gson:
            // .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideEofferApiService(retrofit: Retrofit): EofferApiService {
        return retrofit.create(EofferApiService::class.java)
    }
}
