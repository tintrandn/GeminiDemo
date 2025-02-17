package com.example.aitripdemo.triplist

import android.util.Log
import com.example.aitripdemo.triplist.accommodations.AccommodationViewModel
import com.example.aitripdemo.triplist.activities.ActivityViewModel
import com.example.aitripdemo.triplist.foods.FoodViewModel
import com.example.aitripdemo.triplist.header.HeaderViewModel
import com.example.aitripdemo.triplist.places.PlaceViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class TripViewModelFactory @Inject constructor() {

    fun createViewModel(jsonText: String): List<TripViewModel> {
        Log.d("TinTHT", "jsonText $jsonText")
        val viewModels = mutableListOf<TripViewModel>()

        val gson = Gson()
        try {
            val trip = if (jsonText.startsWith("[")) {
                val tripListType = object : TypeToken<List<Trip>>() {}.type
                val tripList: List<Trip> = gson.fromJson(jsonText, tripListType)
                tripList[0]
            } else {
                gson.fromJson(jsonText, Trip::class.java)
            }

            trip.apply {
                viewModels.add(HeaderViewModel(tripName, duration))
                val activityString = buildString {
                    activities.forEach {
                        appendLine(it)
                        appendLine()
                    }
                }
                viewModels.add(ActivityViewModel(activityString))
                viewModels.add(PlaceViewModel(places))
                viewModels.add(FoodViewModel(foodSuggestions))
                viewModels.add(AccommodationViewModel(accommodations))
            }
        } catch (_: Exception) {
        }
        return viewModels
    }
}