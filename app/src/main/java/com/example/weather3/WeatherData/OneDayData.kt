package com.example.weather3.WeatherData

//represent one Day only
data class DayData (
    var time: String,
    var temp_max: Double,
    var temp_min: Double,
    var apparent_temp_max: Double,
    var apparent_temp_min: Double,
    var sunrise: String,
    var sunset: String,
    var precip_sum: Double,
    var rain_sum: Double,
    var wind_max: Double,
    var weathercode: Int,

    var Hours:ArrayList<HourData>
        )

//represent one hour only:
data class HourData(
    var time: String,
    var temp: Double,
    var app_temp: Double,
    var pressure: Double,
    var rain: Double,
    var humidity: Int,
    var visibility: Double,
    var windspeed: Double,
    var winddirection: Int,
    var weathercode: Int,
)


