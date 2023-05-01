package com.example.weather3

import android.text.Editable
import com.example.weather3.WeatherData.DATACLASS
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastAPI {
    @GET("v1/forecast?&hourly_units=&hourly=temperature_2m&hourly=apparent_temperature&hourly=relativehumidity_2m&hourly=pressure_msl&hourly=windspeed_10m&hourly=winddirection_10m&hourly=rain&hourlyvisibility&hourly=weathercode&daily_units&daily=temperature_2m_max&daily=temperature_2m_min&daily=apparent_temperature_max&daily=apparent_temperature_min&daily=precipitation_sum&daily=rain_sum&daily=sunrise&daily=sunset&daily=windspeed_10m_max&daily=weathercode")
    fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
        @Query("timezone") tzone: String
    ): Call<DATACLASS>


//    @GET("v1/forecast?hourly=temperature_2m,apparent_temperature,relativehumidity_2m,pressure_msl,windspeed_10m,winddirection_10m,rain,visibility,weathercode&daily=temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,precipitation_sum,rain_sum,sunrise,sunset,windspeed_10m_max,weathercode")
//    suspend fun getWeatherData(): Response<DATACLASS>
//
//    companion object {
//
//        operator fun invoke(): ForecastAPI {
//            return Retrofit.Builder()
//                .baseUrl("https://api.open-meteo.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(ForecastAPI::class.java)
//        }
//


}