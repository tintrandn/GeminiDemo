package com.example.aitripdemo.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater.from
import android.widget.LinearLayout
import com.example.aitripdemo.databinding.WidgetFlightAirportBinding
import com.example.aitripdemo.databinding.WidgetFlightAirportBinding.*

class FlightAirportWidget : LinearLayout {

    private lateinit var binding: WidgetFlightAirportBinding

    private var callback: OnOdClickedCallback? = null

    private var originCountryCode: String = ""
    private var destinationCountryCode: String = ""

    constructor(context: Context?) : super(context) {
        setup()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        setup()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs,
        defStyleAttr
    ) {
        setup()
    }

    private fun setup() {
        binding = inflate(from(context), this, true)
        setOnclick()
    }

    private fun setOnclick() {
        onDestinationContainerClicked()
        onOriginContainerClicked()
    }

    fun setCallback(callback: OnOdClickedCallback) {
        this.callback = callback
    }

    private fun onDestinationContainerClicked() {
        binding.odselectorDestinationContainer.setOnClickListener {
            callback?.onDestinationClicked()
        }

        binding.odselectorDestinationEmptyContainer.setOnClickListener {
            callback?.onDestinationClicked()
        }

    }

    private fun onOriginContainerClicked() {
        binding.odselectorOriginContainer.setOnClickListener {
            callback?.onOriginClicked()
        }

        binding.odselectorOriginEmptyContainer.setOnClickListener {
            callback?.onOriginClicked()
        }
    }

    fun setOriginAirport(airportCode: String, cityName: String, originCountryCode: String) {
        binding.odselectorOriginAirportCode.text = airportCode
        binding.odselectorOriginCityName.text = cityName
        binding.odselectorOriginEmptyContainer.visibility = GONE
        binding.odselectorOriginContainer.visibility = VISIBLE
        this.originCountryCode = originCountryCode
    }

    fun setDestinationAirport(
        airportCode: String,
        cityName: String,
        destinationCountryCode: String
    ) {
        binding.odselectorDestinationAirportCode.text = airportCode
        binding.odselectorDestinationCityName.text = cityName
        binding.odselectorDestinationEmptyContainer.visibility = GONE
        binding.odselectorDestinationContainer.visibility = VISIBLE
        this.destinationCountryCode = destinationCountryCode
    }

    fun getOriginCityName(): String {
        return binding.odselectorOriginCityName.text.toString()
    }


    fun getDestinationCityName(): String {
        return binding.odselectorDestinationCityName.text.toString()
    }


    interface OnOdClickedCallback {

        fun onOriginClicked()
        fun onDestinationClicked()
    }
}
