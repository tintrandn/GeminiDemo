package com.example.aitripdemo.triplist.places

import com.example.aitripdemo.triplist.TripViewModel
import com.example.aitripdemo.triplist.TripViewModelType.Companion.PLACES

class PlaceViewModel(val places: List<String>) : TripViewModel() {
    override fun getViewType() = PLACES
}