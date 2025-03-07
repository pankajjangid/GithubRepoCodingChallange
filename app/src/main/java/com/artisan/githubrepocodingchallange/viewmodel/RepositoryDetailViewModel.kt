package com.artisan.githubrepocodingchallange.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.artisan.githubrepocodingchallange.model.Repository
import com.artisan.githubrepocodingchallange.repo.RepositoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for the repository details screen.
 * It loads repository data reactively from the local database.
 */
class RepositoryDetailViewModel(application: Application, private val repoId: Long) :
    AndroidViewModel(application) {
    private val repository = RepositoryRepository(application)

    val repositoryLiveData: LiveData<Repository> = repository.getRepositoryById(repoId)

    /**
     * Toggle bookmark status of the repository.
     */
    fun toggleBookmark(repo: Repository) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.toggleBookmark(repo)
        }
    }
}
