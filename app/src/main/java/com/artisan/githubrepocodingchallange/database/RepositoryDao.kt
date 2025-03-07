package com.artisan.githubrepocodingchallange.database


import androidx.lifecycle.LiveData
import androidx.room.*
import com.artisan.githubrepocodingchallange.model.Repository

/**
 * Data Access Object (DAO) for repository operations.
 */
@Dao
interface RepositoryDao {
    @Query("SELECT * FROM repositories")
    fun getAllRepositories(): LiveData<List<Repository>>

    @Query("SELECT * FROM repositories WHERE id = :id")
    fun getRepositoryById(id: Long): LiveData<Repository>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repos: List<Repository>)

    @Update
    suspend fun updateRepository(repo: Repository)
}
