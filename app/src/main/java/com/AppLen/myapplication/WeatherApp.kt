package com.AppLen.myapplication

import android.opengl.Visibility
import java.net.IDN
import java.util.TimeZone

data class WeatherApp(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timeZone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
