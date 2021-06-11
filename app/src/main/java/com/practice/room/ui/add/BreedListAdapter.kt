package com.practice.room.ui.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.practice.room.data.room.DogBreed
import com.practice.room.databinding.ListItemBreedBinding

class BreedListAdapter : ListAdapter<DogBreed, BreedListAdapter.ViewHolder>(BreedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return BreedListAdapter.ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(private val binding: ListItemBreedBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DogBreed) {
            binding.breed = item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBreedBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class BreedDiffCallback : DiffUtil.ItemCallback<DogBreed>() {
        override fun areItemsTheSame(oldItem: DogBreed, newItem: DogBreed): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DogBreed, newItem: DogBreed): Boolean {
            return oldItem == newItem
        }
    }
}