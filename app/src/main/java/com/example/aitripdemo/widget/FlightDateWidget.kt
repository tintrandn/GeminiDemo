package com.example.aitripdemo.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater.from
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat.getFont
import com.example.aitripdemo.R
import com.example.aitripdemo.databinding.WidgetFlightDateBinding
import com.example.aitripdemo.databinding.WidgetFlightDateBinding.*
import com.example.aitripdemo.support.DateFormatter
import org.threeten.bp.LocalDate

class FlightDateWidget : LinearLayout {

    private lateinit var binding: WidgetFlightDateBinding
    private var callback: OnDateClickedCallback? = null
    private var dateFormatter: DateFormatter? = null

    companion object {

        const val LOCALIZED_DEPARTURE_DATE_PATTERN = "d MMMM yyyy"
    }

    constructor(context: Context) : super(context) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        setup()
    }

    fun setCallback(callback: OnDateClickedCallback) {
        this.callback = callback
    }


    fun setDepartureDate(formattedDate: String?) {
        binding.departurereturndateDepartureDate.text = formattedDate
        setStyleForTextViewWithValue(binding.departurereturndateDepartureDate)
        if (!isValidDates(getDepartureDate(), getReturnDate())) {
            binding.departurereturndateReturnDate.text = context.getString(R.string.date_select)
        }
    }

    private fun isValidDates(departureDate: LocalDate?, returnDate: LocalDate?): Boolean {
        return departureDate != null &&
                returnDate != null &&
                (departureDate.isEqual(returnDate) || departureDate.isBefore(returnDate))
    }

    fun setReturnDate(formattedDate: String?) {
        binding.departurereturndateReturnDate.text = formattedDate
        setStyleForTextViewWithValue(binding.departurereturndateReturnDate)
    }

    private fun getDepartureDate(): LocalDate? {
        val departureDateString = binding.departurereturndateDepartureDate.text.toString()
        return dateFormatter?.formatDateWithLocale(
            departureDateString,
            LOCALIZED_DEPARTURE_DATE_PATTERN,
            "en_UK"
        )
    }

    private fun getReturnDate(): LocalDate? {
        val returnDateString = binding.departurereturndateReturnDate.text.toString()
        return dateFormatter?.formatDateWithLocale(
            returnDateString,
            LOCALIZED_DEPARTURE_DATE_PATTERN,
            "en_UK"
        )
    }

    fun getDepartureDateString(): String {
        return binding.departurereturndateDepartureDate.text.toString()
    }

    fun getReturnDateString(): String {
        return binding.departurereturndateReturnDate.text.toString()
    }

    private fun setup() {
        binding = inflate(from(context), this, true)
        setOnClick()
    }

    private fun setOnClick() {
        onReturnDateClicked()
        onDepartureDateClicked()
    }

    private fun setStyleForTextViewWithValue(textView: TextView) {
        textView.setTextColor(getColor(context, R.color.text_blue))
        textView.typeface = getFont(context, R.font.proxima_nova_bold)
    }


    private fun onDepartureDateClicked() {
        binding.departurereturndateDepartureContainer.setOnClickListener {
            callback?.onDepartureDateClicked()
        }
    }

    private fun onReturnDateClicked() {
        binding.departurereturndateReturnContainer.setOnClickListener {
            callback?.onReturnDateClicked()
        }
    }

    interface OnDateClickedCallback {

        fun onDepartureDateClicked()
        fun onReturnDateClicked()
    }
}
