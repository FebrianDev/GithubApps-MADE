package com.febrian.core.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.febrian.core.core.domain.model.Github
import com.febrian.core.databinding.ItemListBinding

class GithubAdapter : RecyclerView.Adapter<GithubAdapter.ListViewHolder>() {

    private var listData = ArrayList<Github>()
    var onItemClick: ((Github) -> Unit)? = null

    fun setData(newListData: ArrayList<Github>) {
        val diffResult = DiffUtil.calculateDiff(RepoDiffCallback(listData, newListData))
        listData = newListData
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ListViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Github) {
            Glide.with(itemView.context)
                .load(data.avatar)
                .into(binding.image)
            binding.username.text = data.username
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GithubAdapter.ListViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: GithubAdapter.ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = if (listData.size > 10) 10 else listData.size

    class RepoDiffCallback(
        private val oldItems: ArrayList<Github>,
        private val newItems: ArrayList<Github>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldItems.size


        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].username == newItems[newItemPosition].username

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems == newItems

    }

}