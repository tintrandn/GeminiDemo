package com.example.aitripdemo.triplist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.aitripdemo.databinding.AccommodationViewBinding
import com.example.aitripdemo.databinding.ActivitiesViewBinding
import com.example.aitripdemo.databinding.FoodsViewBinding
import com.example.aitripdemo.databinding.HeaderViewBinding
import com.example.aitripdemo.databinding.PlacesViewBinding
import com.example.aitripdemo.triplist.TripViewModelType.Companion.ACCOMMODATIONS
import com.example.aitripdemo.triplist.TripViewModelType.Companion.ACTIVITIES
import com.example.aitripdemo.triplist.TripViewModelType.Companion.FOODS
import com.example.aitripdemo.triplist.TripViewModelType.Companion.HEADER
import com.example.aitripdemo.triplist.TripViewModelType.Companion.PLACES
import com.example.aitripdemo.triplist.accommodations.AccommodationViewHolder
import com.example.aitripdemo.triplist.accommodations.AccommodationViewModel
import com.example.aitripdemo.triplist.activities.ActivityViewHolder
import com.example.aitripdemo.triplist.activities.ActivityViewModel
import com.example.aitripdemo.triplist.commonitem.CommonItemAdapter
import com.example.aitripdemo.triplist.foods.FoodViewHolder
import com.example.aitripdemo.triplist.foods.FoodViewModel
import com.example.aitripdemo.triplist.header.HeaderViewHolder
import com.example.aitripdemo.triplist.header.HeaderViewModel
import com.example.aitripdemo.triplist.places.PlaceViewHolder
import com.example.aitripdemo.triplist.places.PlaceViewModel
import javax.inject.Inject

class TripAdapter @Inject constructor(
    private val foodAdapter: CommonItemAdapter,
    private val placesAdapter: CommonItemAdapter,
    private val accommodationAdapter: CommonItemAdapter
) : RecyclerView.Adapter<ViewHolder>() {

    private var viewModels: List<TripViewModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            HEADER -> {
                val binding = HeaderViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                HeaderViewHolder(binding)
            }

            ACTIVITIES -> {
                val binding = ActivitiesViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                ActivityViewHolder(binding)
            }

            PLACES -> {
                val binding = PlacesViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                PlaceViewHolder(binding)
            }

            FOODS -> {
                val binding = FoodsViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                FoodViewHolder(binding)
            }

            ACCOMMODATIONS -> {
                val binding = AccommodationViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                AccommodationViewHolder(binding)
            }

            else -> throw IllegalStateException(
                "Unknown type $viewType for TripAdapter"
            )
        }
    }

    override fun getItemCount(): Int {
        return viewModels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            HEADER -> {
                (holder as HeaderViewHolder).bindView(
                    viewModels[position] as HeaderViewModel
                )
            }

            ACTIVITIES -> {
                (holder as ActivityViewHolder).bindView(
                    viewModels[position] as ActivityViewModel
                )
            }

            PLACES -> {
                (holder as PlaceViewHolder).bindView(
                    viewModels[position] as PlaceViewModel,
                    placesAdapter
                )
            }

            FOODS -> {
                (holder as FoodViewHolder).bindView(
                    viewModels[position] as FoodViewModel,
                    foodAdapter
                )
            }

            ACCOMMODATIONS -> {
                (holder as AccommodationViewHolder).bindView(
                    viewModels[position] as AccommodationViewModel,
                    accommodationAdapter
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewModels[position].getViewType()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setViewModels(viewModels: List<TripViewModel>) {
        this.viewModels = viewModels
        notifyDataSetChanged()
    }

}