package com.applen.myapplication.model.network
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    fun getWeatherData(
        @Query("city") city : String,
        @Query("appid") apiKey : String,
        @Query("units") units : String
    ) : Call<Weanow>
}