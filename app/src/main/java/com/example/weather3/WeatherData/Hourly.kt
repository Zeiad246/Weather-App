package com.example.weather3.WeatherData

data class Hourly(
    val apparent_temperature: List<Double>,
    val pressure_msl: List<Double>,
    val rain: List<Double>,
    val relativehumidity_2m: List<Int>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val visibility: List<Double>,
    val weathercode: List<Int>,
    val winddirection_10m: List<Int>,
    val windspeed_10m: List<Double>
)