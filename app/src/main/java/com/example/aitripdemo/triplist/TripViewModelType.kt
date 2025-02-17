package com.example.aitripdemo.triplist

import androidx.annotation.IntDef
import com.example.aitripdemo.triplist.TripViewModelType.Companion.ACCOMMODATIONS
import com.example.aitripdemo.triplist.TripViewModelType.Companion.ACTIVITIES
import com.example.aitripdemo.triplist.TripViewModelType.Companion.FOODS
import com.example.aitripdemo.triplist.TripViewModelType.Companion.HEADER
import com.example.aitripdemo.triplist.TripViewModelType.Companion.PLACES

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    HEADER, ACTIVITIES, PLACES, FOODS, ACCOMMODATIONS
)
annotation class TripViewModelType {
    companion object {
        const val HEADER = 0
        const val ACTIVITIES = 1
        const val PLACES = 2
        const val FOODS = 3
        const val ACCOMMODATIONS = 4
    }
}