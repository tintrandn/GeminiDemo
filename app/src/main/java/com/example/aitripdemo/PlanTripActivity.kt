package com.example.aitripdemo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.aitripdemo.IntentExtra.ADULT_COUNT
import com.example.aitripdemo.IntentExtra.ARR_CITY
import com.example.aitripdemo.IntentExtra.CB_CLASS
import com.example.aitripdemo.IntentExtra.CHILD_COUNT
import com.example.aitripdemo.IntentExtra.DEP_DATE
import com.example.aitripdemo.IntentExtra.DEP_CITY
import com.example.aitripdemo.IntentExtra.INF_COUNT
import com.example.aitripdemo.IntentExtra.RET_DATE
import com.example.aitripdemo.databinding.ActivityPlanTripBinding
import com.example.aitripdemo.helper.fetchPlaceImage
import com.example.aitripdemo.modules.Application
import com.example.aitripdemo.triplist.TripAdapter
import com.example.aitripdemo.triplist.TripViewModelFactory
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.FunctionType
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.Schema
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlanTripActivity : AppCompatActivity() {

    @Inject
    lateinit var tripViewModelFactory: TripViewModelFactory

    @Inject
    lateinit var tripAdapter: TripAdapter

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val model = GenerativeModel(
        "gemini-2.0-flash",
        BuildConfig.API_KEY,
        generationConfig = generationConfig {
            temperature = 1f
            topK = 40
            topP = 0.95f
            maxOutputTokens = 8192
            responseMimeType = "application/json"
            responseSchema = Schema(
                name = "travelItinerary",
                description = "A structured travel itinerary",
                type = FunctionType.OBJECT,
                properties = mapOf(
                    "tripName" to Schema(
                        name = "tripName",
                        description = "Name of the trip",
                        type = FunctionType.STRING,
                    ),
                    "duration" to Schema(
                        name = "duration",
                        description = "Duration of the trip",
                        type = FunctionType.STRING,
                    ),
                    "theme" to Schema(
                        name = "theme",
                        description = "Thematic focus of the trip",
                        type = FunctionType.STRING,
                    ),
                    "places" to Schema(
                        name = "places",
                        description = "List of places to visit in activities",
                        type = FunctionType.ARRAY,
                        items = Schema(
                            name = "place",
                            description = "A place of interest",
                            type = FunctionType.STRING
                        )
                    ),
                    "activities" to Schema(
                        name = "activities",
                        description = "List of planned activities",
                        type = FunctionType.ARRAY,
                        items = Schema(
                            name = "activity",
                            description = "An activity during the trip",
                            type = FunctionType.STRING
                        )
                    ),
                    "foodSuggestions" to Schema(
                        name = "foodSuggestions",
                        description = "Recommended food items or restaurants",
                        type = FunctionType.ARRAY,
                        items = Schema(
                            name = "food",
                            description = "A food recommendation",
                            type = FunctionType.STRING
                        )
                    ),
                    "accommodations" to Schema(
                        name = "accommodations",
                        description = "Suggested places to stay",
                        type = FunctionType.ARRAY,
                        items = Schema(
                            name = "accommodation",
                            description = "A lodging recommendation",
                            type = FunctionType.STRING
                        )
                    )
                ),
                required = listOf(
                    "tripName",
                    "duration",
                    "theme",
                    "places",
                    "activities",
                    "foodSuggestions",
                    "accommodations"
                )
            )
        })

    private val chatHistory = listOf<Content>()
    private val chat = model.startChat(chatHistory)

    private lateinit var binding: ActivityPlanTripBinding
    private lateinit var arrCity: String

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as Application).getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityPlanTripBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tripList.adapter = tripAdapter

        val depCity = intent.getStringExtra(DEP_CITY)!!
        arrCity = intent.getStringExtra(ARR_CITY)!!
        val depDate = intent.getStringExtra(DEP_DATE)!!
        val retDate = intent.getStringExtra(RET_DATE)!!
        val adultCount = intent.getStringExtra(ADULT_COUNT)!!
        val childCount = intent.getStringExtra(CHILD_COUNT)!!
        val infantCount = intent.getStringExtra(INF_COUNT)!!
        val cabinClass = intent.getStringExtra(CB_CLASS)!!

        if (arrCity.isNotEmpty() && depDate.isNotEmpty() && retDate.isNotEmpty()) {
            binding.promptEditText.setText(
                getString(R.string.prompt_default, arrCity, depDate, retDate)
            )
            if (depCity.isNotEmpty() && adultCount.isNotEmpty() && childCount.isNotEmpty() && infantCount.isNotEmpty() && cabinClass.isNotEmpty()) {
                val prompt = String.format(
                    defaultPrompt,
                    adultCount,
                    childCount,
                    infantCount,
                    depCity,
                    arrCity,
                    cabinClass,
                    depDate,
                    retDate,
                    arrCity
                )
                generate(prompt)
            }

        }

        binding.buttonClear.setOnClickListener {
            binding.promptEditText.setText("")
        }

        binding.buttonGenerate.setOnClickListener {
            val prompt = binding.promptEditText.text.toString()
            if (prompt.isNotEmpty()) {
                generate(prompt)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    private fun generate(prompt: String?) {
        prompt?.let {
            coroutineScope.launch {
                showLoading(true)
                val response = generateChat(prompt)
                fetPlaceImage(arrCity)

                response.text?.let { jsonText ->
                    val viewModels = tripViewModelFactory.createViewModel(jsonText)
                    tripAdapter.setViewModels(viewModels)
                }

                showLoading(false)
            }
        }
    }

    private suspend fun generateChat(prompt: String): GenerateContentResponse {
        return withContext(Dispatchers.IO) {
            chat.sendMessage(prompt)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.promptEditText.isEnabled = false
            binding.buttonGenerate.isEnabled = false
            binding.buttonClear.isEnabled = false
            binding.lottieLoader.visibility = View.VISIBLE
            binding.imageCardView.visibility = View.GONE
            binding.tripList.visibility = View.GONE
            binding.lottieLoader.playAnimation()
        } else {
            binding.promptEditText.isEnabled = true
            binding.buttonGenerate.isEnabled = true
            binding.buttonClear.isEnabled = true
            binding.lottieLoader.visibility = View.GONE
            binding.imageCardView.visibility = View.VISIBLE
            binding.tripList.visibility = View.VISIBLE
            binding.lottieLoader.playAnimation()
        }
    }

    private fun fetPlaceImage(placeName: String) {
        fetchPlaceImage(placeName) { imageUrl ->
            runOnUiThread {
                if (imageUrl != null) {
                    binding.imageTitle.text = placeName
                    binding.imageCardView.visibility = View.VISIBLE
                    Glide.with(this).load(imageUrl).placeholder(R.drawable.landscape_placeholder)
                        .error(R.drawable.landscape_placeholder).into(binding.imageView)
                } else {
                    binding.imageCardView.visibility = View.GONE
                    Glide.with(binding.root.context).load(R.drawable.landscape_placeholder)
                        .into(binding.imageView)
                    Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}

object IntentExtra {
    const val DEP_CITY = "DEP_CITY"
    const val ARR_CITY = "ARR_CITY"
    const val DEP_DATE = "DEP_DATE"
    const val RET_DATE = "RET_DATE"
    const val ADULT_COUNT = "ADULT_COUNT"
    const val CHILD_COUNT = "CHILD_COUNT"
    const val INF_COUNT = "INF_COUNT"
    const val CB_CLASS = "CB_CLASS"
}