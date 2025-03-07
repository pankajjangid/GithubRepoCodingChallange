package com.artisan.githubrepocodingchallange.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Data model for a GitHub repository.
 * The property isBookmarked is used locally to mark a repository as bookmarked.
 */
@Entity(tableName = "repositories")
data class Repository(
    @PrimaryKey val id: Long,
    val name: String,
    @SerializedName("stargazers_count") val stars: Int,
    var isBookmarked: Boolean = false
)