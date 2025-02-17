package com.example.aitripdemo.triplist.activities

import com.example.aitripdemo.triplist.TripViewModel
import com.example.aitripdemo.triplist.TripViewModelType.Companion.ACTIVITIES

class ActivityViewModel(val activity: String) : TripViewModel() {
    override fun getViewType() = ACTIVITIES
}