package com.example.aitripdemo.triplist.header

import androidx.recyclerview.widget.RecyclerView
import com.example.aitripdemo.R
import com.example.aitripdemo.databinding.HeaderViewBinding

class HeaderViewHolder(
    val binding: HeaderViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(model: HeaderViewModel) {
        binding.tripName.text = model.tripName
        binding.tripDuration.text = itemView.resources.getString(R.string.duration, model.duration)
    }
}