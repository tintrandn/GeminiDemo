package com.example.aitripdemo.triplist.header

import com.example.aitripdemo.triplist.TripViewModel
import com.example.aitripdemo.triplist.TripViewModelType.Companion.HEADER

class HeaderViewModel(val tripName: String, val duration: String) : TripViewModel() {
    override fun getViewType() = HEADER
}