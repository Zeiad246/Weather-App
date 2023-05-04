package com.example.weather3

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import com.example.weather3.Adapters.DaysAdapter
import com.example.weather3.Fragments.LocationFragment
import com.example.weather3.Search.LocationAPI
import com.example.weather3.Search.LocationDATA
import com.example.weather3.WeatherData.DATACLASS
import com.example.weather3.WeatherData.DayData
import com.example.weather3.WeatherData.ForecastAPI
import com.example.weather3.databinding.ActivityLocationBinding
import com.example.weather3.databinding.ActivityWeatherBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityLocationBinding

    lateinit var sharedPref : SharedPreferences

    lateinit var we : WeatherActivity

    lateinit var locationData : LocationDATA

    lateinit var forecastData : DATACLASS

    val baseUrl = "https://api.open-meteo.com/"

    val baseUrl2 = "https://geocoding-api.open-meteo.com/"


    var lat: String = "31.00"
    var long: String = "30.00"
    var timezone: String = "Africa/Cairo"
    var city: String = "Cairo"
    var country: String = "Egypt"

    var Latii: Double = 31.0
    var Longii: Double = 30.0
    var Timezoneii: String = "Africa/Cairo"
    var Cityii: String = "Cairo"
    var Countryii: String = "Egypt"

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)



    override fun onPause() {
        super.onPause()
        saveData()
    }

    override fun onResume() {
        super.onResume()
        retrieveData()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityLocationBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)


        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = LocationFragment()




        mainBinding.apply {

            latitudeBox.text = lat.toEditable()
            longitudeBox.text = long.toEditable()
            timezoneBox.text = timezone.toEditable()
            CityBox.text = city.toEditable()
            CountryBox.text = country.toEditable()

        }

        mainBinding.OKButton.setOnClickListener {

            lat = mainBinding.latitudeBox.text.toString()
            long = mainBinding.longitudeBox.text.toString()
            timezone = mainBinding.timezoneBox.text.toString()
            city = mainBinding.CityBox.text.toString()
            country = mainBinding.CountryBox.text.toString()

            locationer()


            val intent = Intent(applicationContext, WeatherActivity::class.java)

            intent.apply {
                putExtra("lat cord", lat.toDouble())
                putExtra("longi cord", long.toDouble())
                putExtra("timezone", timezone)
                putExtra("city name", city)
                putExtra("country name", country)
            }
            startActivity(intent)

        }


        mainBinding.SignOutButton.setOnClickListener {

            Firebase.auth.signOut()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)

        }




        // Declaring fragment manager from making data
        // transactions using the custom fragment





    }











    fun locationer(){

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val retrofitAPI : ForecastAPI = retrofit.create(ForecastAPI::class.java)
        val call : Call<DATACLASS> = retrofitAPI.getWeatherData(Latii, Longii, Timezoneii)

        call.enqueue(object : Callback<DATACLASS>
        {
            override fun onResponse(call: Call<DATACLASS>, response: Response<DATACLASS>) {
                if (!response.isSuccessful)
                {


                    Log.d("Test weather", "unsuccessful")



                }else{
                    Log.d("Test weather", "successful")
                    forecastData = response.body() as DATACLASS


//                    runOnUiThread {

//                    organizeForecastData(forecastData)
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
                Log.e("Error weather", t.message.toString())
            }

        })

        val retrofit2 = Retrofit.Builder()
            .baseUrl(baseUrl2)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val locationAPI: LocationAPI = retrofit2.create(LocationAPI::class.java)

        val call2 : Call<LocationDATA> = locationAPI.getLocation(Latii, Longii, Timezoneii, Cityii, Countryii)
        call2.enqueue(object : Callback<LocationDATA>
        {
            override fun onResponse(call: Call<LocationDATA>, response: Response<LocationDATA>) {
                if (!response.isSuccessful)
                {


                    Log.d("Test location", "unsuccessful")



                }else{
                    Log.d("Test location", "successful")
                    locationData = response.body() as LocationDATA

                    if (locationData.results[0].latitude.toString() != mainBinding.latitudeBox.text.toString()){

                        mainBinding.latitudeBox.setText(locationData.results[0].latitude.toString())
                        mainBinding.longitudeBox.setText(locationData.results[0].longitude.toString())
                        mainBinding.timezoneBox.setText(locationData.results[0].timezone)
                        mainBinding.CityBox.setText(locationData.results[0].name)
                        mainBinding.CountryBox.setText(locationData.results[0].country)


                    }else if (locationData.results[0].longitude.toString() != mainBinding.longitudeBox.text.toString()){

                        mainBinding.latitudeBox.setText(locationData.results[0].latitude.toString())
                        mainBinding.longitudeBox.setText(locationData.results[0].longitude.toString())
                        mainBinding.timezoneBox.setText(locationData.results[0].timezone)
                        mainBinding.CityBox.setText(locationData.results[0].name)
                        mainBinding.CountryBox.setText(locationData.results[0].country)

                    }else if (locationData.results[0].timezone != mainBinding.timezoneBox.toString()){

                        mainBinding.latitudeBox.setText(locationData.results[0].latitude.toString())
                        mainBinding.longitudeBox.setText(locationData.results[0].longitude.toString())
                        mainBinding.timezoneBox.setText(locationData.results[0].timezone)
                        mainBinding.CityBox.setText(locationData.results[0].name)
                        mainBinding.CountryBox.setText(locationData.results[0].country)

                    }else if (locationData.results[0].name != mainBinding.CityBox.text.toString()){

                        mainBinding.latitudeBox.setText(locationData.results[0].latitude.toString())
                        mainBinding.longitudeBox.setText(locationData.results[0].longitude.toString())
                        mainBinding.timezoneBox.setText(locationData.results[0].timezone)
                        mainBinding.CityBox.setText(locationData.results[0].name)
                        mainBinding.CountryBox.setText(locationData.results[0].country)

                    }else if (locationData.results[0].country != mainBinding.CountryBox.toString()){

                        mainBinding.latitudeBox.setText(locationData.results[0].latitude.toString())
                        mainBinding.longitudeBox.setText(locationData.results[0].longitude.toString())
                        mainBinding.timezoneBox.setText(locationData.results[0].timezone)
                        mainBinding.CityBox.setText(locationData.results[0].name)
                        mainBinding.CountryBox.setText(locationData.results[0].country)

                    }


//                    runOnUiThread {


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

            override fun onFailure(call: Call<LocationDATA>, t: Throwable) {
                t.printStackTrace()
                Log.e("Error", t.message.toString())
            }

        })
    }

    fun saveData(){
        sharedPref=this.getSharedPreferences("saveData", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.apply{
            putString("lat cord", mainBinding.latitudeBox.text.toString())
            putString("long cord", mainBinding.longitudeBox.text.toString())
            putString("timezone", mainBinding.timezoneBox.text.toString())
            putString("city name", mainBinding.CityBox.text.toString())
            putString("country name", mainBinding.CountryBox.text.toString())

            apply()
        }


    }


    fun retrieveData(){

        sharedPref = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        val lat = sharedPref.getString("lat cord", null)
        val long = sharedPref.getString("long cord", null)
        val timezone = sharedPref.getString("timezone", null)
        val city = sharedPref.getString("city name", null)
        val country = sharedPref.getString("country name", null)

        mainBinding.latitudeBox.setText(lat)
        mainBinding.longitudeBox.setText(long)
        mainBinding.timezoneBox.setText(timezone)
        mainBinding.CityBox.setText(city)
        mainBinding.CountryBox.setText(country)

    }

}