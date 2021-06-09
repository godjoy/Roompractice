package com.practice.room.ui.dogs


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.practice.room.data.room.Dog
import com.practice.room.databinding.ListItemDogsBinding

class DogsAdapter : ListAdapter<Dog, DogsAdapter.ViewHolder>(DogDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun submitList(list: MutableList<Dog>?) {
        Log.d("submitList", "data: ${list?.size} firstItem: ${list?.get(0)}")
        super.submitList(list)
    }

    class ViewHolder private constructor(private val binding: ListItemDogsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Dog) {
            binding.dog = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemDogsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class DogDiffCallback : DiffUtil.ItemCallback<Dog>() {
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem == newItem
        }
    }
}