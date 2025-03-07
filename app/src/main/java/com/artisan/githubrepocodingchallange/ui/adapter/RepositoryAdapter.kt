package com.artisan.githubrepocodingchallange.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artisan.githubrepocodingchallange.model.Repository
import com.artisan.githubrepocodingchallange.databinding.ItemRepositoryBinding

/**
 * RecyclerView adapter for displaying GitHub repositories.
 */
class RepositoryAdapter(
    private var items: List<Repository>,
    private val onItemClicked: (Repository) -> Unit
) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    inner class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repository) {
            binding.tvRepoName.text = repo.name
            binding.tvStars.text = "Stars: ${repo.stars}"
            // Display bookmark icon if repository is bookmarked
            binding.ivBookmark.apply {
                if (repo.isBookmarked) {
                    setImageResource(android.R.drawable.star_on)
                } else {
                    setImageResource(android.R.drawable.star_off)
                }
            }
            binding.root.setOnClickListener { onItemClicked(repo) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding =
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newItems: List<Repository>) {
        items = newItems
        notifyDataSetChanged()
    }
}
