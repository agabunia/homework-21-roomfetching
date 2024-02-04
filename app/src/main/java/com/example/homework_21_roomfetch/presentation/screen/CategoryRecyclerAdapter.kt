package com.example.homework_21_roomfetch.presentation.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_21_roomfetch.databinding.CategoryLayoutBinding

class CategoryRecyclerAdapter(private var categoryList: List<String>) :
    RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return CategoryViewHolder(CategoryLayoutBinding.inflate(inflate, parent, false))
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryModel = categoryList[position]
        holder.bind(categoryModel)
    }

    fun updateList(newList: List<String>) {
        categoryList = newList
        notifyDataSetChanged()
    }

    var onItemClicked: ((String) -> Unit)? = null

    inner class CategoryViewHolder(private val binding: CategoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: String) {
            binding.tvBtn.text = category
            binding.layoutBtn.setOnClickListener {
                onItemClicked?.invoke(category)
            }
        }
    }
}