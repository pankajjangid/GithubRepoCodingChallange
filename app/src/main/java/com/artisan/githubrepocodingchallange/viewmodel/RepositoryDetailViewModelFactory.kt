package com.artisan.githubrepocodingchallange.viewmodel


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory to create RepositoryDetailViewModel with a repository ID.
 */
class RepositoryDetailViewModelFactory(
    private val application: Application,
    private val repoId: Long
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepositoryDetailViewModel::class.java)) {
            return RepositoryDetailViewModel(application, repoId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
