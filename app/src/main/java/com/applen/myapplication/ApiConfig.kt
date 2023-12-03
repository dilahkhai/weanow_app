package com.applen.myapplication

import com.applen.myapplication.model.network.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

    fun fetchWeatherData(): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val newRequest = it.request().newBuilder()
                    .addHeader("X-API-KEY", "1ea61dd254b330833407fbab1e002412")
                    .build()
                it.proceed(newRequest)
            }
            .readTimeout(10, TimeUnit.SECONDS) //time to wait for the response to the API
            .retryOnConnectionFailure(false)
            .build() //save the detail request

        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }
}