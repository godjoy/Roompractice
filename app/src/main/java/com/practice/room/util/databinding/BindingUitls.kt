package com.practice.room.util.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

@BindingAdapter("bind:setVisibility")
fun setVisible(view: View, visibility: Boolean) {
    if (visibility)
        view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}