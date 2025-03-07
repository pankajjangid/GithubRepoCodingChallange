package com.artisan.githubrepocodingchallange.ui


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.artisan.githubrepocodingchallange.model.Repository
import com.artisan.githubrepocodingchallange.viewmodel.RepositoryDetailViewModel
import com.artisan.githubrepocodingchallange.viewmodel.RepositoryDetailViewModelFactory
import com.artisan.githubrepocodingchallange.databinding.ActivityRepositoryDetailBinding

/**
 * RepositoryDetailActivity displays details for a selected repository and allows bookmarking.
 */
class RepositoryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepositoryDetailBinding
    private lateinit var viewModel: RepositoryDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get repository ID passed from MainActivity
        val repoId = intent.getLongExtra("REPO_ID", -1)
        if (repoId == -1L) finish()

        // Use a ViewModelFactory to pass repoId to the ViewModel
        val factory = RepositoryDetailViewModelFactory(application, repoId)
        viewModel = viewModels<RepositoryDetailViewModel> { factory }.value

        // Observe repository details
        viewModel.repositoryLiveData.observe(this) { repo ->
            repo?.let { updateUI(it) }
        }

        // Toggle bookmark when button is clicked
        binding.btnBookmark.setOnClickListener {
            viewModel.repositoryLiveData.value?.let { repo ->
                viewModel.toggleBookmark(repo)
            }
        }
    }

    private fun updateUI(repo: Repository) {
        binding.tvRepoName.text = repo.name
        binding.tvStars.text = "Stars: ${repo.stars}"
        binding.btnBookmark.text = if (repo.isBookmarked) "Remove Bookmark" else "Add Bookmark"
    }
}
