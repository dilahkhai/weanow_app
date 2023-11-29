package com.AppLen.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.AppLen.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchWeatherData()
    }

    private fun fetchWeatherData() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build().create(ApiService::class.java)
        val response = retrofit.getWeatherData("jakarta", "1ea61dd254b330833407fbab1e002412", "metric")
        response.enqueue(object : Callback<Weanow>{
            override fun onResponse(call: Call<Weanow>, response: Response<Weanow>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    val temperature = responseBody.main.temp.toString()

                }
            }

            override fun onFailure(call: Call<Weanow>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}