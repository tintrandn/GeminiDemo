package com.example.aitripdemo.triplist.places

import androidx.recyclerview.widget.RecyclerView
import com.example.aitripdemo.databinding.PlacesViewBinding
import com.example.aitripdemo.triplist.commonitem.CommonItemAdapter

class PlaceViewHolder(
    val binding: PlacesViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(model: PlaceViewModel, commonItemAdapter: CommonItemAdapter) {
        binding.placesList.adapter = commonItemAdapter
        commonItemAdapter.setViewModels(model.places)
    }
}