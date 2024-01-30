package com.example.homework_21_roomfetch.presentation.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_21_roomfetch.R
import com.example.homework_21_roomfetch.databinding.ClothLayoutBinding
import com.example.homework_21_roomfetch.presentation.model.Clothes

class ClothesRecyclerAdapter :
    ListAdapter<Clothes, ClothesRecyclerAdapter.ClothesViewHolder>(ClothDiffUtil()) {

    class ClothDiffUtil : DiffUtil.ItemCallback<Clothes>() {
        override fun areItemsTheSame(oldItem: Clothes, newItem: Clothes): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Clothes, newItem: Clothes): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ClothesViewHolder(ClothLayoutBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: ClothesViewHolder, position: Int) {
        val currentCloth = getItem(position)
        holder.bind(currentCloth)
    }

    inner class ClothesViewHolder(private val binding: ClothLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context
        fun bind(cloth: Clothes) {
            binding.apply {
                Glide.with(context).load(cloth.cover).into(sivClothImage)
                tvTitle.text = cloth.title
                tvPrice.text = cloth.price
                if (cloth.favorite) {
                    ivHeartIcon.setImageResource(R.drawable.heart_clicked)
                } else {
                    ivHeartIcon.setImageResource(R.drawable.heart_unclicked)
                }
            }
        }
    }
}