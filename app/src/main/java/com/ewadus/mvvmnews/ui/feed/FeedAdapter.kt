package com.ewadus.mvvmnews.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ewadus.mvvmnews.data.model.news.Article
import com.ewadus.mvvmnews.databinding.ItemViewFeedBinding

class FeedAdapter(private val onclickListener: OnclickListener) :
    ListAdapter<Article, FeedAdapter.ViewHolder>(MainDiffCallback()) {

    class ViewHolder(private val binding: ItemViewFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article, onClickListener: OnclickListener) {
            binding.article = article
            binding.executePendingBindings()


            binding.btnRead.setOnClickListener {
                onClickListener.onClickItemView(adapterPosition)
            }
            binding.imgShare.setOnClickListener {
                onClickListener.onClickShare(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewFeedBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position), onclickListener)
    }
}

interface OnclickListener {
    fun onClickItemView(position: Int)
    fun onClickShare(position: Int)
}

class MainDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}