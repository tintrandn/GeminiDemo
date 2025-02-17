package com.example.aitripdemo

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aitripdemo.IntentExtra.ADULT_COUNT
import com.example.aitripdemo.IntentExtra.ARR_CITY
import com.example.aitripdemo.IntentExtra.CB_CLASS
import com.example.aitripdemo.IntentExtra.CHILD_COUNT
import com.example.aitripdemo.IntentExtra.DEP_DATE
import com.example.aitripdemo.IntentExtra.DEP_CITY
import com.example.aitripdemo.IntentExtra.INF_COUNT
import com.example.aitripdemo.IntentExtra.RET_DATE
import com.example.aitripdemo.databinding.ActivityMainBinding
import com.example.aitripdemo.support.CabinClassCode
import com.example.aitripdemo.widget.FlightDateWidget.OnDateClickedCallback
import com.example.aitripdemo.widget.FlightAirportWidget.OnOdClickedCallback
import java.util.Calendar

class MainActivity : AppCompatActivity(),
    OnOdClickedCallback,
    OnDateClickedCallback {

    private val airports = mapOf(
        "SIN" to "Singapore",
        "DAD" to "Da Nang",
        "HND" to "Tokyo",
        "LAX" to "Los Angeles",
        "DXB" to "Dubai",
        "LHR" to "London",
        "CDG" to "Paris",
        "SYD" to "Sydney",
        "JFK" to "New York",
        "HKG" to "Hong Kong"
    )

    private lateinit var binding: ActivityMainBinding
    private var cabinClass: String? = null
    private var selectedAirPortCode: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.flightAirportWidget.setCallback(this)
        binding.flightDateWidget.setCallback(this)

        binding.flightCabinClassWidget.setOnClickListener {
            showCabinPassengerDialog()
        }

        binding.layoutFlightRecommender.root.setOnClickListener {
            startPlanTrip()
        }
    }

    override fun onOriginClicked() {
        showAirportDialog(true)
    }

    override fun onDestinationClicked() {
        showAirportDialog(false)
    }

    override fun onDepartureDateClicked() {
        openDatePicker(true)
    }

    override fun onReturnDateClicked() {
        openDatePicker(false)
    }

    private fun startPlanTrip() {
        val intent = Intent(this, PlanTripActivity::class.java)
        intent.putExtra(DEP_CITY, binding.flightAirportWidget.getOriginCityName())
        intent.putExtra(ARR_CITY, binding.flightAirportWidget.getDestinationCityName())
        intent.putExtra(
            DEP_DATE,
            binding.flightDateWidget.getDepartureDateString()
        )
        intent.putExtra(
            RET_DATE,
            binding.flightDateWidget.getReturnDateString()
        )
        intent.putExtra(
            ADULT_COUNT,
            binding.flightCabinClassWidget.adult.toString()
        )
        intent.putExtra(
            CHILD_COUNT,
            binding.flightCabinClassWidget.child.toString()
        )
        intent.putExtra(
            INF_COUNT,
            binding.flightCabinClassWidget.infant.toString()
        )
        intent.putExtra(CB_CLASS, cabinClass.toString())
        startActivity(intent)
    }

    private fun showAirportDialog(isOrigin: Boolean) {
        val airportList = if (selectedAirPortCode != null) {
            airports.filterKeys { it != selectedAirPortCode }
                .map { "${it.key} - ${it.value}" }.toTypedArray()
        } else {
            airports.map { "${it.key} - ${it.value}" }.toTypedArray()
        }

        AlertDialog.Builder(this)
            .setTitle("Select Airport")
            .setItems(airportList) { _, which ->
                val selectedAirport = airportList[which].split(" - ")
                val airportCode = selectedAirport[0]
                val cityName = selectedAirport[1]
                selectedAirPortCode = airportCode
                if (isOrigin) {
                    binding.flightAirportWidget.setOriginAirport(
                        airportCode,
                        cityName,
                        airportCode
                    )
                } else {
                    binding.flightAirportWidget.setDestinationAirport(
                        airportCode,
                        cityName,
                        airportCode
                    )
                }
            }
            .show()
    }

    private fun openDatePicker(isDeparture: Boolean) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                if (isDeparture) {
                    binding.flightDateWidget.setDepartureDate(formattedDate)
                } else {
                    binding.flightDateWidget.setReturnDate(formattedDate)
                }
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun showCabinPassengerDialog() {
        val dialogView = layoutInflater.inflate(R.layout.passenger_cabin_dialog, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        val radioGroupCabin = dialogView.findViewById<RadioGroup>(R.id.radioGroupCabin)

        val txtAdultCount = dialogView.findViewById<TextView>(R.id.txtAdultCount)
        val txtChildCount = dialogView.findViewById<TextView>(R.id.txtChildCount)
        val txtInfantCount = dialogView.findViewById<TextView>(R.id.txtInfantCount)

        val btnPlusAdult = dialogView.findViewById<Button>(R.id.btnPlusAdult)
        val btnMinusAdult = dialogView.findViewById<Button>(R.id.btnMinusAdult)

        val btnPlusChild = dialogView.findViewById<Button>(R.id.btnPlusChild)
        val btnMinusChild = dialogView.findViewById<Button>(R.id.btnMinusChild)

        val btnPlusInfant = dialogView.findViewById<Button>(R.id.btnPlusInfant)
        val btnMinusInfant = dialogView.findViewById<Button>(R.id.btnMinusInfant)

        btnPlusAdult.setOnClickListener {
            val adultCount = txtAdultCount.text.toString().toInt()
            val updateCount = adultCount + 1
            txtAdultCount.text = updateCount.toString()
            btnMinusAdult.isEnabled = true
        }
        btnMinusAdult.setOnClickListener {
            var adultCount = txtAdultCount.text.toString().toInt()
            if (adultCount == 1) {
                btnMinusAdult.isEnabled = false
            } else {
                txtAdultCount.text = (--adultCount).toString()
                if (adultCount == 1) btnMinusAdult.isEnabled = false
            }
        }

        btnPlusChild.setOnClickListener {
            val childCount = txtChildCount.text.toString().toInt()
            val updateCount = childCount + 1
            txtChildCount.text = updateCount.toString()
            btnMinusChild.isEnabled = true
        }
        btnMinusChild.setOnClickListener {
            var childCount = txtChildCount.text.toString().toInt()
            if (childCount == 0) {
                btnMinusChild.isEnabled = false
            } else {
                txtChildCount.text = (--childCount).toString()
                if (childCount == 0) btnMinusChild.isEnabled = false
            }

        }

        btnPlusInfant.setOnClickListener {
            val infantCount = txtInfantCount.text.toString().toInt()
            val updateCount = infantCount + 1
            txtInfantCount.text = updateCount.toString()
            btnMinusInfant.isEnabled = true
        }
        btnMinusInfant.setOnClickListener {
            var infantCount = txtInfantCount.text.toString().toInt()
            if (infantCount == 0) {
                btnMinusInfant.isEnabled = false
            } else {
                txtInfantCount.text = (--infantCount).toString()
                if (infantCount == 0) btnMinusInfant.isEnabled = false
            }
        }

        dialogView.findViewById<Button>(R.id.btnDone).setOnClickListener {
            cabinClass =
                dialogView.findViewById<RadioButton>(radioGroupCabin.checkedRadioButtonId).text.toString()
            val cabinClassCode = when (cabinClass) {
                "Economy" -> CabinClassCode.ECONOMY
                "Premium Economy" -> CabinClassCode.PREMIUM_ECONOMY
                "Business" -> CabinClassCode.BUSINESS
                else -> CabinClassCode.FIRST
            }
            binding.flightCabinClassWidget.setPassengerCabinClass(
                txtAdultCount.text.toString().toInt(),
                txtChildCount.text.toString().toInt(),
                txtInfantCount.text.toString().toInt(),
                cabinClassCode
            )
            dialog.dismiss()
        }
        dialog.show()
    }

}
