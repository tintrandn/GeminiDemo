package com.example.aitripdemo.triplist

abstract class TripViewModel {

    @TripViewModelType
    abstract fun getViewType(): Int
}