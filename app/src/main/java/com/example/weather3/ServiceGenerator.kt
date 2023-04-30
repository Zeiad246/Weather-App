package com.example.weather3

import com.example.weather3.WeatherData.DATACLASS
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {



    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val retrofitAPI : ForecastAPI = retrofit.create(ForecastAPI::class.java)
    val call : Call<DATACLASS> = retrofitAPI.getWeatherData(52.01, 51.00, "Africa/Cairo")





}