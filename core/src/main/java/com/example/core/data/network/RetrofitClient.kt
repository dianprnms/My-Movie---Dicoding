package com.example.core.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "ca0d93ea30b7015390f9ae8d859f6863"

    // Certificate Pinning dengan SHA-256 Public Key
    private val certificatePinner = CertificatePinner.Builder()
        .add("api.themoviedb.org", "sha256/k1Hdw5sdSn5kh/gemLVSQD/P4i4IBQEY1tW4WNxh9XM=") // Public key SHA-256 yang telah Anda dapatkan
        .build()

    // Logging interceptor untuk debugging
    private val logging: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    // Interceptor untuk menambahkan API Key ke setiap request
    private val apiKeyInterceptor = Interceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        chain.proceed(request)
    }

    // OkHttpClient dengan Certificate Pinning
    private val client = OkHttpClient.Builder()
        .certificatePinner(certificatePinner) // Menambahkan certificate pinning
        .addInterceptor(apiKeyInterceptor) // Menambahkan API Key Interceptor
        .addInterceptor(logging) // Menambahkan Logging Interceptor untuk debugging
        .build()

    // Retrofit Instance
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    // Film API Service
    @Singleton
    @Provides
    fun provideFilmApiService(retrofit: Retrofit): FilmAPIService =
        retrofit.create(FilmAPIService::class.java)
}
