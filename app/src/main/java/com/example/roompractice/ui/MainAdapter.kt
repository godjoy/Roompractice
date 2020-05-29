package com.example.roompractice.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roompractice.R
import com.example.roompractice.databinding.ItemDogBinding
import com.example.roompractice.db.entity.Dog

class MainAdapter(private val click: (position: Int) -> Unit): RecyclerView.Adapter<MainAdapter.DogViewHolder>() {

    private val item = mutableListOf<Dog>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder = DogViewHolder(parent, click)

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        item[position].let {
            it.Id.toString()
            holder.bind(it)
        }
    }

    fun setData(newData: MutableList<Dog>) {
        newData.let {
            item.clear()
            item.addAll(newData)
            notifyDataSetChanged()
        }
    }

    fun getDataAt(position: Int) = item[position]

    class DogViewHolder(parent: ViewGroup, click: (position: Int) -> Unit): BaseViewHolder<ItemDogBinding>(
        R.layout.item_dog, parent) {

        init {
            itemView.setOnClickListener { click(adapterPosition) }
        }

        fun bind(item: Dog) {
            binding.itemDog = item
        }
    }
}