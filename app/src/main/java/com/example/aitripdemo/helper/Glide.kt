package com.example.aitripdemo.helper

import com.example.aitripdemo.BuildConfig
import com.example.aitripdemo.service.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun fetchPlaceImage(imageName: String, callback: (String?) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response =
                RetrofitInstance.api.searchPhotos(imageName, apiKey = BuildConfig.UNS_KEY)
            val imageUrl = response.results.firstOrNull()?.urls?.regular
            callback(imageUrl)
        } catch (e: Exception) {
            e.printStackTrace()
            callback(null)
        }
    }
}