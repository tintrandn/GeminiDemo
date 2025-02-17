package com.example.aitripdemo.support

import android.content.Context
import androidx.annotation.IntRange
import com.example.aitripdemo.R
import javax.inject.Inject

class PassengerSelectionCountFormatter @Inject constructor(private val context: Context) {

    fun getFormattedPassengerSelection(
        @IntRange(from = 1, to = 9) adult: Int,
        @IntRange(from = 0, to = 9) child: Int,
        @IntRange(from = 0, to = 9) infant: Int
    ): String {

        val builder = StringBuilder()

        if (adult > 0) {
            builder.append("$adult ")
            if (adult == 1) {
                builder.append(context.resources.getString(R.string.search_flight_passenger_type_adult))
            } else {
                builder.append(context.resources.getString(R.string.search_flight_passenger_type_adults))
            }
        }
        if (child > 0) {
            builder.append(", $child ")
            if (child == 1) {
                builder.append(context.resources.getString(R.string.search_flight_passenger_type_child))
            } else {
                builder.append(context.resources.getString(R.string.search_flight_passenger_type_children))
            }
        }
        if (infant > 0) {
            builder.append(", $infant ")
            if (infant == 1) {
                builder.append(context.resources.getString(R.string.search_flight_passenger_type_infant))
            } else {
                builder.append(context.resources.getString(R.string.search_flight_passenger_type_infants))
            }
        }
        return builder.toString()
    }
}