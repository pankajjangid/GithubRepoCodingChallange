package com.artisan.githubrepocodingchallange.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object to create the Retrofit instance.
 */
object RetrofitInstance {
    private const val BASE_URL = "https://api.github.com/"

    val api: GitHubApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApiService::class.java)
    }
}
