package io.nirmal.flash.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.nirmal.flash.databinding.ItemNewsPreviewBinding
import io.nirmal.flash.models.Article

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    inner class ArticleViewHolder(val binding: ItemNewsPreviewBinding) : RecyclerView.ViewHolder(binding.root)
    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemNewsPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        with(holder) {
            with(differ.currentList[position]) {
                Glide.with(holder.itemView.context).load(differ.currentList[position].urlToImage).into(binding.articleImage)
                binding.articleTitle.text = differ.currentList[position].title
            }
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article)-> Unit)? = null
    fun setOnItemClickListener(listener: (Article)->Unit) {
        onItemClickListener = listener
    }
}