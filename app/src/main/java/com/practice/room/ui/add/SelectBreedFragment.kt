package com.practice.room.ui.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.practice.room.R
import com.practice.room.data.injection.Injection
import com.practice.room.databinding.FragmentSelectBreedBinding
import com.practice.room.ui.dogs.DogsViewModel
import com.practice.room.util.ViewModelFactory
import com.practice.room.util.base.BaseFragment

class SelectBreedFragment : BaseFragment<AddDogViewModel, FragmentSelectBreedBinding>(R.layout.fragment_select_breed) {
    private val adapter by lazy { BreedListAdapter() }

    override val viewModel: AddDogViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(Injection.provideDogRepository(requireContext())))
            .get(AddDogViewModel::class.java)
    }

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBreed.apply {
            adapter = this@SelectBreedFragment.adapter
        }
    }

    override fun observeData() {
        viewModel.breedList.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }
}