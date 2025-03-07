package com.artisan.githubrepocodingchallange.network


import com.artisan.githubrepocodingchallange.model.Repository
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit API interface for GitHub.
 * The endpoint supports pagination via page and per_page parameters.
 */
interface GitHubApiService {
    @GET("orgs/square/repos")
    suspend fun getSquareRepositories(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20
    ): List<Repository>
}
