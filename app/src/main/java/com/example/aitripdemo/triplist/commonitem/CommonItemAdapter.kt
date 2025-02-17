package com.example.aitripdemo.triplist.commonitem

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aitripdemo.databinding.CommonItemViewBinding
import javax.inject.Inject

class CommonItemAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var viewModels: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CommonItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CommonItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return viewModels.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CommonItemViewHolder).bindView(viewModels[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setViewModels(viewModels: List<String>) {
        this.viewModels = viewModels
        notifyDataSetChanged()
    }
}