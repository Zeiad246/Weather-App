package com.example.weather3.WeatherData

data class HourlyUnits(
    val apparent_temperature: String,
    val pressure_msl: String,
    val rain: String,
    val relativehumidity_2m: String,
    val temperature_2m: String,
    val time: String,
    val visibility: String,
    val weathercode: String,
    val winddirection_10m: String,
    val windspeed_10m: String
)