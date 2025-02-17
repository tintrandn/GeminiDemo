package com.example.aitripdemo.triplist.accommodations

import androidx.recyclerview.widget.RecyclerView
import com.example.aitripdemo.databinding.AccommodationViewBinding
import com.example.aitripdemo.triplist.commonitem.CommonItemAdapter

class AccommodationViewHolder(
    val binding: AccommodationViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(viewModel: AccommodationViewModel, commonItemAdapter: CommonItemAdapter) {
        binding.accommodationList.adapter = commonItemAdapter
        commonItemAdapter.setViewModels(viewModel.accommodations)
    }
}