package com.artisan.githubrepocodingchallange.repo


import android.content.Context
import androidx.lifecycle.LiveData
import com.artisan.githubrepocodingchallange.database.AppDatabase
import com.artisan.githubrepocodingchallange.model.Repository
import com.artisan.githubrepocodingchallange.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository class that handles data operations.
 * It fetches data from the network and stores it in the local database.
 */
class RepositoryRepository(context: Context) {
    private val dao = AppDatabase.getDatabase(context).repositoryDao()

    val allRepositories: LiveData<List<Repository>> = dao.getAllRepositories()

    /**
     * Fetch repositories from GitHub API for the given page and save them to the database.
     */
    suspend fun fetchAndStoreRepositories(page: Int) {
        withContext(Dispatchers.IO) {
            try {
                val repos = RetrofitInstance.api.getSquareRepositories(page)
                dao.insertRepositories(repos)
            } catch (e: Exception) {
                // Handle errors (e.g., log or pass error state to UI)
                e.printStackTrace()
            }
        }
    }

    fun getRepositoryById(id: Long): LiveData<Repository> {
        return dao.getRepositoryById(id)
    }

    /**
     * Toggle bookmark state for a repository.
     */
    suspend fun toggleBookmark(repo: Repository) {
        withContext(Dispatchers.IO) {
            repo.isBookmarked = !repo.isBookmarked
            dao.updateRepository(repo)
        }
    }
}
