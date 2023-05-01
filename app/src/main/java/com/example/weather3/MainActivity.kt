package com.example.weather3

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.text.Editable
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather3.WeatherData.DATACLASS
import com.example.weather3.WeatherData.DayData
import com.example.weather3.WeatherData.HourData
import com.example.weather3.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.TimeZone
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding


    val baseUrl = "https://api.open-meteo.com/"
    lateinit var forecastData : DATACLASS
    var daydata = ArrayList<DayData>()

    var DayDate = ArrayList<String>()
    var hightemp = ArrayList<Double>()
    var lowtemp = ArrayList<Double>()

    lateinit var Dadapter: DaysAdapter




//    val lat = editText.getDouble()

    lateinit var Lat : EditText
    lateinit var Long : EditText
    lateinit var Timezone : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.DayCardsList.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        Dadapter = DaysAdapter(daydata)
        mainBinding.DayCardsList.adapter = Dadapter


//        GlobalScope.launch(Dispatchers.IO) {
//            val time = measureTimeMillis {
//                val RV = async { showForecasts() }
////                val LocationDialog = async { showDialog() }
//                RV.await()
////                LocationDialog.await()
//            }
//
//            }
//        }


//            val intent = Intent(this, ForecastActivity::class.java)
//            startActivity(intent)

        showForecasts()

        mainBinding.OpenButton.setOnClickListener {

//            showDialog()

        }





    }

//    fun EditText.getDouble(): Double = try {
//        text.toString().toDouble()
//    } catch (e: NumberFormatException) {
//        e.printStackTrace()
//        0.0
//    }

    fun EditText.toDouble() = toString().toDouble()

    fun test() {

    }
//    fun showDialog() {
//
//
//        val dialog = Dialog(this@MainActivity)
//        dialog.requestWindowFeature(
//            Window.FEATURE_NO_TITLE
//        )
//        dialog.setCancelable(false)
//        dialog.setContentView(R.layout.location_dialog)
//        dialog.window!!.attributes.windowAnimations=R.style.dialoganimation
//        var OkButton = dialog.findViewById<Button>(R.id.cancel_button)
//
//        dialog.window!!.setGravity(Gravity.TOP)
//        OkButton.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialog.show()
//
//        Lat = dialog.findViewById<EditText>(R.id.latitudeBox)
//        Long = dialog.findViewById<EditText>(R.id.longitudeBox)
//        Timezone = dialog.findViewById<EditText>(R.id.timezoneBox)
//
//
//
//
//    }


    fun showForecasts(){

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val retrofitAPI : ForecastAPI = retrofit.create(ForecastAPI::class.java)
        val call : Call<DATACLASS> = retrofitAPI.getWeatherData(52.01, 51.00, "Africa/Cairo")



        call.enqueue(object : Callback<DATACLASS>
        {
            override fun onResponse(call: Call<DATACLASS>, response: Response<DATACLASS>) {
                if (!response.isSuccessful)
                {

                    mainBinding.hoursHighestTemp.text = "error"
                    Log.d("Test", "unsuccessful")



                }else{
                    Log.d("Test", "successful")
                    forecastData=response.body() as DATACLASS


//                    runOnUiThread {
                        mainBinding.hoursHighestTemp.text = " " + forecastData.hourly.temperature_2m[3].toString() + "°"
                        organizeForecastData(forecastData)
//                    }

//                    GlobalScope.launch {
//
//                        val temp = async { mainBinding.hoursHighestTemp.text = " " + forecastData.hourly.temperature_2m[0].toString() + "°" }
//                        val RV = async {  organizeForecastData(forecastData, daydata) }
//                        temp.await()
//                        RV.await()
//                    }



                }

            }

            override fun onFailure(call: Call<DATACLASS>, t: Throwable) {
                t.printStackTrace()
                Log.e("Error", t.message.toString())
            }

        })
    }

        fun organizeForecastData(forecastData: DATACLASS) {
            for (i in 0..6) {
                var hoursList: ArrayList<HourData> = ArrayList()
                for (j in 0..23) {
                    var hour = forecastData.hourly.time[i * 24 + j]
                    hour = hour.substring(hour.indexOf("T") + 1)

                    var hourdata = HourData(
                        time = hour,
                        temp = forecastData.hourly.temperature_2m[i * 24 + j],
                        pressure = forecastData.hourly.pressure_msl[i * 24 + j],
                        rain = forecastData.hourly.rain[i * 24 + j],
                        humidity = forecastData.hourly.relativehumidity_2m[i * 24 + j],
//                        visibility = forecastData.hourly.visibility[i*24+j],
                        windspeed = forecastData.hourly.windspeed_10m[i * 24 + j],
                        winddirection = forecastData.hourly.winddirection_10m[i * 24 + j],
                        weathercode = forecastData.hourly.weathercode[i * 24 + j]

                    )
                    hoursList.add(hourdata)
                }

                var day = DayData(
                    time = forecastData.daily.time[i],
                    temp_max = forecastData.daily.temperature_2m_max[i],
                    temp_min = forecastData.daily.temperature_2m_min[i],
                    apparent_temp_max = forecastData.daily.apparent_temperature_max[i],
                    apparent_temp_min = forecastData.daily.apparent_temperature_min[i],
                    sunrise = forecastData.daily.sunrise[i],
                    sunset = forecastData.daily.sunset[i],
                    precip_sum = forecastData.daily.precipitation_sum[i],
                    rain_sum = forecastData.daily.rain_sum[i],
                    weathercode = forecastData.daily.weathercode[i],

                    Hours = hoursList
                )
                daydata.add(day)
                Dadapter.notifyDataSetChanged()
            }

        }

}

