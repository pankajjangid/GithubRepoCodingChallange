package com.artisan.githubrepocodingchallange.utils


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Abstract class for implementing endless scrolling in a RecyclerView.
 */
abstract class EndlessRecyclerOnScrollListener : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

        if (totalItemCount <= lastVisibleItem + 3) { // Load more when 3 items from the end
            onLoadMore()
        }
    }
    abstract fun onLoadMore()
}
