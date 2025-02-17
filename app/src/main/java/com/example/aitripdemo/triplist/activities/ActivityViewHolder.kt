package com.example.aitripdemo.triplist.activities

import androidx.recyclerview.widget.RecyclerView
import com.example.aitripdemo.databinding.ActivitiesViewBinding

class ActivityViewHolder(
    val binding: ActivitiesViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(viewModel: ActivityViewModel) {
        binding.activity.text = viewModel.activity
    }
}