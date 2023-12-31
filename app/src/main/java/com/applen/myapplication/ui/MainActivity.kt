package com.applen.myapplication.ui

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.applen.myapplication.R
import com.applen.myapplication.databinding.ActivityMainBinding
import com.applen.myapplication.model.network.ApiConfig.fetchWeatherData
import com.applen.myapplication.model.network.ApiService
import com.applen.myapplication.model.network.Weanow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchWeatherData()
        SearchCity()

        val fabProfileGroup: View = binding.fabProfileGroup
        fabProfileGroup.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(intent)
        }
    }





    private fun SearchCity() {
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    fetchWeatherData(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun fetchWeatherData(cityName : String) {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build().create(ApiService::class.java)
        val response = retrofit.getWeatherData(cityName, "1ea61dd254b330833407fbab1e002412", "metric")
        response.enqueue(object : Callback<Weanow>{
            override fun onResponse(call: Call<Weanow>, response: Response<Weanow>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    val temperature = responseBody.main.temp.toString()
                    val humidity = responseBody.main.humidity
                    val windSpeed = responseBody.wind.speed
                    val sunrise = responseBody.sys.sunrise
                    val sunset = responseBody.sys.sunset
                    val seaLevel = responseBody.main.pressure
                    val condition = responseBody.weather.firstOrNull()?.main?: "unknown"
                    val maxTemp = responseBody.main.temp_max
                    val minTemp = responseBody.main.temp_min

                    binding.tvTemp.text = "$temperature °C"
                    binding.tvMaxTemp.text = "Max Temp: $maxTemp °C"
                    binding.tvMinTemp.text = "Max Temp: $minTemp °C"
                    binding.tvHumidity.text = "$humidity"
                    binding.tvWindSpeed.text = "$windSpeed"
                    binding.tvSunRise.text = "$sunrise"
                    binding.tvSunSet.text = "$sunset"
                    binding.tvSeaLevel.text = "$seaLevel hPa"
                    binding.tvCondition.text = condition
                    binding.tvDay.text = dayName(System.currentTimeMillis())
                        binding.tvDate.text = date()
                        binding.tvCityName.text = "$cityName"

//                    Log.d(TAG, "onResponse: $temperature")

                    changeImageAccordingToWeatherCondition(condition)
                }
            }

            override fun onFailure(call: Call<Weanow>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun changeImageAccordingToWeatherCondition(conditions : String) {
        when (conditions) {
            "Clear Sky", "Sunny", "Clear" -> {
                binding.ivWeather.setImageResource(R.drawable.sunny)
            }

            "Partly Clouds", "Clouds", "Overcast", "Mist", "Foggy" -> {
                binding.ivWeather.setImageResource(R.drawable.cloudy)
            }

            "Light Rain", "Drizzle", " Moderate Rain", "Showers", "Heavy Rain" -> {
                binding.ivWeather.setImageResource(R.drawable.rainy)
            }

            "Light Snow", "Moderate Snow", "Heavy Snow", "Blizzard" -> {
                binding.ivWeather.setImageResource(R.drawable.snow)
            }
            }
        }
    }

    private fun date() : String {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return sdf.format((Date()))
    }

    fun dayName(timestamp: Long) : String {
        val sdf = android.icu.text.SimpleDateFormat("EEEE", Locale.getDefault())
        return sdf.format((Date()))
    }
