package com.example.weather3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.weather3.WeatherData.DATACLASS
import com.example.weather3.WeatherData.DayData
import com.example.weather3.databinding.ActivityForecastBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForecastActivity : AppCompatActivity() {

    lateinit var forecastbinding : ActivityForecastBinding

//    val baseUrl = "https://api.open-meteo.com/"
//    lateinit var forecastData : DATACLASS
//    var daydata = ArrayList<DayData>()
//    lateinit var Dadapter: DaysAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        forecastbinding = ActivityForecastBinding.inflate(layoutInflater)
        val view = forecastbinding.root
        setContentView(view)

//        val retrofit = Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create()).build()
//        val retrofitAPI : ForecastAPI = retrofit.create(ForecastAPI::class.java)
//        val call : Call<DATACLASS> = retrofitAPI.getWeatherData(52.01, 51.00, "Africa/Cairo")
//
//
//
//        call.enqueue(object : Callback<DATACLASS>
//        {
//            override fun onResponse(call: Call<DATACLASS>, response: Response<DATACLASS>) {
//                if (!response.isSuccessful)
//                {
//
//                    forecastbinding.hoursHighestTemp.text = "error"
//
//
//
//                }else{
//                    forecastData=response.body() as DATACLASS
//
//
////                    runOnUiThread {
////                        forecastbinding.hoursHighestTemp.text = " " + forecastData.hourly.temperature_2m[0].toString() + "°"
////                        organizeForecastData(forecastData, daydata)
////                    }
//
//
//                    forecastbinding.hoursHighestTemp.text = " " + forecastData.hourly.temperature_2m[0].toString() + "°"
////                    organizeForecastData(forecastData, daydata)
//
//                }
//
//            }
//
//            override fun onFailure(call: Call<DATACLASS>, t: Throwable) {
//                t.printStackTrace()
//                Log.e("Error", t.message.toString())
//            }
//
//        })



    }
}