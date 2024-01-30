package com.example.homework_21_roomfetch.presentation.screen

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework_21_roomfetch.databinding.FragmentStoreBinding
import com.example.homework_21_roomfetch.presentation.event.StoreEvent
import com.example.homework_21_roomfetch.presentation.base.BaseFragment
import com.example.homework_21_roomfetch.presentation.state.StoreState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoreFragment : BaseFragment<FragmentStoreBinding>(FragmentStoreBinding::inflate) {
    private val viewModel: StoreViewModel by viewModels()
    private lateinit var clothesRecyclerAdapter: ClothesRecyclerAdapter

    override fun bind() {
        setAdapter()
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listState.collect{
                    handleState(it)
                }
            }
        }
    }

    private fun setAdapter() {
        clothesRecyclerAdapter = ClothesRecyclerAdapter()
        binding.apply {
            rvClothes.layoutManager = GridLayoutManager(requireContext(), 2)
            rvClothes.setHasFixedSize(true)
            rvClothes.adapter = clothesRecyclerAdapter
        }
        viewModel.onEvent(StoreEvent.FetchClothes)
    }

    private fun handleState(state: StoreState) {
        state.clothes?.let {
            clothesRecyclerAdapter.submitList(it)
        }

        state.errorMessage?.let {
            toastMessage(it)
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}