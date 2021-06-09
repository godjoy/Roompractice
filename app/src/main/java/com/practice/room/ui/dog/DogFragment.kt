package com.practice.room.ui.dog

import androidx.lifecycle.ViewModelProvider
import com.practice.room.R
import com.practice.room.util.base.BaseFragment
import com.practice.room.data.injection.Injection
import com.practice.room.databinding.FragmentDogBinding
import com.practice.room.viewmodel.DogViewModel
import com.practice.room.viewmodel.ViewModelFactory

class DogFragment: BaseFragment<DogViewModel, FragmentDogBinding>(R.layout.fragment_dog){

    override val viewModel: DogViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(Injection.provideDogRepository(requireContext())))
            .get(DogViewModel::class.java)
    }

    override fun setViewModel() {
        binding.vm = viewModel
    }
}