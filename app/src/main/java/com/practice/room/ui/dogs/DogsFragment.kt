package com.practice.room.ui.dogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.room.R
import com.practice.room.data.model.DogResult
import com.practice.room.util.base.BaseFragment
import com.practice.room.data.injection.Injection
import com.practice.room.data.room.Dog
import com.practice.room.databinding.FragmentDogsBinding
import com.practice.room.viewmodel.DogsViewModel
import com.practice.room.viewmodel.ViewModelFactory
import okhttp3.internal.notify

class DogsFragment: BaseFragment<DogsViewModel, FragmentDogsBinding>(R.layout.fragment_dogs){

    private val adapter by lazy { DogsAdapter() }

    override val viewModel: DogsViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(Injection.provideDogRepository(requireContext())))
            .get(DogsViewModel::class.java)
    }

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDogs.apply {
            adapter = this@DogsFragment.adapter
        }
    }

    override fun observeData() {
        viewModel.dogs.observe(viewLifecycleOwner, {
            when(it) {
                is DogResult.Success -> {
                    Log.d("DogsFragment", "success dataSize: ${it.data.size}")
                    adapter.submitList(it.data.toMutableList())
                }
                is DogResult.Error -> {
                    Log.d("DogsFragment", "Error ${it.error}")
                }
                is DogResult.InProgress -> {
                    Log.d("DogsFragment", "LoadData~")
                }
            }
        })
    }
}