package com.AppLen.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//9a54f508c9d202d7fb2b9c06702c7406
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<Weanow>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}