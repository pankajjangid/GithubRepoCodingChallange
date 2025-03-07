package com.artisan.githubrepocodingchallange.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.artisan.githubrepocodingchallange.viewmodel.RepositoryListViewModel
import com.artisan.githubrepocodingchallange.databinding.ActivityMainBinding
import com.artisan.githubrepocodingchallange.ui.adapter.RepositoryAdapter
import com.artisan.githubrepocodingchallange.utils.EndlessRecyclerOnScrollListener

/**
 * MainActivity displays a list of Square's GitHub repositories.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: RepositoryListViewModel by viewModels()
    private lateinit var adapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RepositoryAdapter(emptyList()) { repo ->
            // Open RepositoryDetailActivity when item is clicked
            val intent = Intent(this, RepositoryDetailActivity::class.java)
            intent.putExtra("REPO_ID", repo.id)
            startActivity(intent)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Observe repositories list changes
        viewModel.repositories.observe(this, Observer { repos ->
            adapter.updateList(repos)
        })

        // Load initial page
        viewModel.fetchNextPage()

        // Implement pagination when scrolling to the bottom (simplified)
        binding.recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                viewModel.fetchNextPage()
            }
        })
    }
}
