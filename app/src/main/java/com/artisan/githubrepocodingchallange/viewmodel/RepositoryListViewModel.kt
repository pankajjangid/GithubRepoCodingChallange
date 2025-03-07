package com.artisan.githubrepocodingchallange.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.artisan.githubrepocodingchallange.repo.RepositoryRepository
import kotlinx.coroutines.launch

/**
 * ViewModel for the repository list screen.
 */
class RepositoryListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RepositoryRepository(application)

    // Expose LiveData list from Room database
    val repositories = repository.allRepositories

    // Keep track of current page for pagination
    private var currentPage = 1

    /**
     * Fetch next page of repositories.
     */
    fun fetchNextPage() {
        viewModelScope.launch {
            repository.fetchAndStoreRepositories(currentPage)
            currentPage++
        }
    }
}
