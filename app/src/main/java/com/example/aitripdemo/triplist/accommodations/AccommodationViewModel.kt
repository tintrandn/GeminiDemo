package com.example.aitripdemo.triplist.accommodations

import com.example.aitripdemo.triplist.TripViewModel
import com.example.aitripdemo.triplist.TripViewModelType.Companion.ACCOMMODATIONS

class AccommodationViewModel(val accommodations: List<String>) : TripViewModel() {
    override fun getViewType() = ACCOMMODATIONS
}