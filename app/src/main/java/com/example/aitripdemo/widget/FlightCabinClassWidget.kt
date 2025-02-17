package com.example.aitripdemo.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import javax.inject.Inject
import com.example.aitripdemo.support.CabinClassCodeConverter
import android.view.LayoutInflater
import com.example.aitripdemo.databinding.WidgetFlightCabinClassBinding
import com.example.aitripdemo.support.CabinClassCode
import com.example.aitripdemo.support.CabinClassCode.ECONOMY
import com.example.aitripdemo.support.PassengerSelectionCountFormatter
import dagger.android.DaggerApplication

class FlightCabinClassWidget : LinearLayout {

    @Inject
    lateinit var passengerSelectionCountFormatter: PassengerSelectionCountFormatter

    @Inject
    lateinit var cabinClassCodeConverter: CabinClassCodeConverter

    private lateinit var binding: WidgetFlightCabinClassBinding

    private val application: DaggerApplication by lazy {
        val appContext = context.applicationContext
        if (appContext is DaggerApplication) {
            return@lazy appContext
        }
        throw IllegalStateException("Application context does not extend DaggerApplication: $context")
    }

    init {
        application.androidInjector().inject(this)
    }

    var adult: Int = 0
    var child: Int = 0
    var infant: Int = 0

    @CabinClassCode
    var cabinClass: String? = null

    constructor(context: Context?) : super(context) {
        setup()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        setup()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        setup()
    }

    private fun resetPassengerCabinClassWidget() {
        setPassengerCabinClass(1, 0, 0, ECONOMY)
    }

    fun setPassengerCabinClass(
        adult: Int,
        child: Int,
        infant: Int,
        @CabinClassCode cabinClass: String
    ) {
        this.adult = adult
        this.child = child
        this.infant = infant
        this.cabinClass = cabinClass

        val formattedPassengerSelection = passengerSelectionCountFormatter
            .getFormattedPassengerSelection(adult, child, infant)

        binding.flightsearchPassengerSelectionCount.text = formattedPassengerSelection
        binding.flightsearchCabinClassSelection.text = context.getString(
            cabinClassCodeConverter
                .getClassStringRes(cabinClass)
        )
    }

    private fun setup() {
        binding = WidgetFlightCabinClassBinding
            .inflate(LayoutInflater.from(context), this, true)
        resetPassengerCabinClassWidget()
    }
}
