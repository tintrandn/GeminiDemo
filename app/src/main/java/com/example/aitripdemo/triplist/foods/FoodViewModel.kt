package com.example.aitripdemo.triplist.foods

import com.example.aitripdemo.triplist.TripViewModel
import com.example.aitripdemo.triplist.TripViewModelType.Companion.FOODS

class FoodViewModel(val foods: List<String>) : TripViewModel() {
    override fun getViewType() = FOODS
}