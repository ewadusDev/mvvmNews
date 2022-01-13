package com.ewadus.mvvmnews.ui.save

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ewadus.mvvmnews.data.model.news.Article
import com.ewadus.mvvmnews.databinding.ItemViewSaveBinding
import com.ewadus.mvvmnews.ui.feed.MainDiffCallback


class SaveAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Article, SaveAdapter.SaveViewHolder>(MainDiffCallback()) {

    class SaveViewHolder(private val binding: ItemViewSaveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article,onClickListener: OnClickListener) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewSaveBinding.inflate(inflater, parent, false)
        return SaveViewHolder(binding)

    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        holder.bind(getItem(position),onClickListener)
    }
}

interface OnClickListener {
    fun onClickItemView(position: Int)
    fun onClickShare(position: Int)

}


