package com.example.aitripdemo.triplist.commonitem

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aitripdemo.R
import com.example.aitripdemo.databinding.CommonItemViewBinding
import com.example.aitripdemo.helper.fetchPlaceImage

class CommonItemViewHolder(
    val binding: CommonItemViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(model: String) {
        binding.commonItemName.text = model
        fetchPlaceImage(model) { imageUrl ->
            binding.root.post {
                if (imageUrl != null) {
                    Glide.with(binding.root.context)
                        .load(imageUrl)
                        .placeholder(R.drawable.landscape_placeholder)
                        .error(R.drawable.landscape_placeholder)
                        .into(binding.commonItemImage)
                } else {
                    Glide.with(binding.root.context)
                        .load(R.drawable.landscape_placeholder)
                        .into(binding.commonItemImage)
                }
            }
        }
    }
}