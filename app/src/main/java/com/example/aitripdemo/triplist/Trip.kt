package com.example.aitripdemo.triplist

data class Trip(
    val tripName: String,
    val duration: String,
    val theme: String,
    val places: List<String>,
    val activities: List<String>,
    val foodSuggestions: List<String>,
    val accommodations: List<String>
)
