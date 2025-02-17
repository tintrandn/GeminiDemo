package com.example.aitripdemo.triplist.foods

import androidx.recyclerview.widget.RecyclerView
import com.example.aitripdemo.databinding.FoodsViewBinding
import com.example.aitripdemo.triplist.commonitem.CommonItemAdapter

class FoodViewHolder(
    val binding: FoodsViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(model: FoodViewModel, commonItemAdapter: CommonItemAdapter) {
        binding.foodList.adapter = commonItemAdapter
        commonItemAdapter.setViewModels(model.foods)
    }
}