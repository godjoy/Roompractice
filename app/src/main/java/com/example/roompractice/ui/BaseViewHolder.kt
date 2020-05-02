package com.example.roompractice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<B: ViewDataBinding>(@LayoutRes private val layoutId: Int, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(layoutId,viewGroup,false)) {

    protected val binding: B = DataBindingUtil.bind(itemView)!!

}