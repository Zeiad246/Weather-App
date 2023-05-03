package com.example.weather3

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather3.Adapters.DaysAdapter
import com.example.weather3.Fragments.SignInFragmentDirections
import com.example.weather3.WeatherData.DATACLASS
import com.example.weather3.WeatherData.DayData
import com.example.weather3.WeatherData.ForecastAPI
import com.example.weather3.WeatherData.HourData
import com.example.weather3.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(){

    lateinit var mainBinding : ActivityMainBinding

    private lateinit var auth: FirebaseAuth


    val baseUrl = "https://api.open-meteo.com/"
    lateinit var forecastData : DATACLASS
    var daydata = ArrayList<DayData>()

    lateinit var Dadapter: DaysAdapter

    var lat: String = "31.00"
    var long: String = "30.00"
    var timezone: String = "Africa/Cairo"

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        FirebaseApp.initializeApp(this)

        auth = Firebase.auth

        Log.d(ContentValues.TAG,"worksassssssss")
        mainBinding.signInButton.setOnClickListener {
            SignIn()
        }



        mainBinding.signUpButton.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
        }

//        val firstFragment = SignInFragment()
//        supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder, firstFragment)
//            .commit()


//        mainBinding.DayCardsList.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
//        Dadapter = DaysAdapter(daydata, this@MainActivity)
//        mainBinding.DayCardsList.adapter = Dadapter
//
//
//
//
//
//
////        GlobalScope.launch(Dispatchers.IO) {
////            val time = measureTimeMillis {
////                val RV = async { showForecasts() }
//////                val LocationDialog = async { showDialog() }
////                RV.await()
//////                LocationDialog.await()
////            }
////
////            }
////        }
//
//
////            val intent = Intent(this, ForecastActivity::class.java)
////            startActivity(intent)
//
//        mainBinding.latitudeBox2.text = lat.toEditable()
//        mainBinding.longitudeBox2.text = long.toEditable()
//        mainBinding.timezoneBox2.text = timezone.toEditable()
//
//
//
////        mainBinding.OpenButton.setOnClickListener {
////
////            showDialog()
////
////        }
//
//        mainBinding.SignUpButton.setOnClickListener {
//
//            lat = mainBinding.latitudeBox2.text.toString()
//            long = mainBinding.longitudeBox2.text.toString()
//            timezone = mainBinding.timezoneBox2.text.toString()
//            showForecasts()
//        }
//
//
    }

    fun SignIn(){

        val email = mainBinding.emailBox.text.toString()
        val password = mainBinding.passwordBox.text.toString()

        if (email.isNotEmpty()){
            if (password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful) {
                            Log.d(ContentValues.TAG, "MESSAGE: sign in successful")
                            Toast.makeText(applicationContext,"Sign In Successful", Toast.LENGTH_SHORT).show()
                            val user = auth.currentUser

                            if (user != null){
                                val intent = Intent(applicationContext, WeatherActivity::class.java)
                                startActivity(intent)
                            }
                        } else {
                            Log.w(ContentValues.TAG, "MESSAGE: sign in failure", task.exception)
                            Toast.makeText(applicationContext, "Sign In Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(applicationContext, "Please enter password", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(applicationContext, "Please enter email", Toast.LENGTH_SHORT).show()
        }
    }
//
//
////    override fun passData(lat: String, long: String, timezone: String) {
////        val bundle = Bundle()
////        bundle.putString("lat", lat)
////        bundle.putString("long", long)
////        bundle.putString("timezone", timezone)
////        val transaction = this.supportFragmentManager.beginTransaction()
////        val fragmentTwo = WeatherDataFragment()
////        fragmentTwo.arguments = bundle
////        transaction.replace(R.id.mainlayout, fragmentTwo)
////        transaction.addToBackStack(null)
////        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
////        transaction.commit()
////    }
//
//    //    fun EditText.getDouble(): Double = try {
////        text.toString().toDouble()
////    } catch (e: NumberFormatException) {
////        e.printStackTrace()
////        0.0
////    }
//
////    fun EditText.toDouble() = toString().toDouble()
////
////    fun test() {
////
////    }
////
////
////    val lat : String = "52.00"
////    val long : String = "52.00"
////    val timezone : String = "Africa/Cairo"
//
//
////    fun showDialog() {
////

//
////        val dialog = Dialog(this@MainApp)
////
////        val Lat : EditText = dialog.findViewById(R.id.latitudeBox)
////        val Long : EditText = dialog.findViewById(R.id.longitudeBox)
////        val Timezone : EditText = dialog.findViewById(R.id.timezoneBox)
////
////        dialog.requestWindowFeature(
////            Window.FEATURE_NO_TITLE
////        )
////
////        dialog.setCancelable(false)
////        dialog.setContentView(R.layout.location_dialog)
////        dialog.window!!.attributes.windowAnimations = R.style.dialoganimation
////
////
////        dialog.window!!.setGravity(Gravity.TOP)
////
////        dialog.show()
////
////        var OkButton = dialog.findViewById<Button>(R.id.cancel_button)
////
////        OkButton.setOnClickListener {
////
////            dialog.dismiss()
////
////            Lat.setText(Lat.text.toString())
////            Long.setText(Long.text.toString())
////            Timezone.setText(Timezone.text.toString())
////
////
////            showForecasts(Lat.text.toString(), Long.text.toString(), Timezone.text.toString())
////
////
////
////
////        Slat = Lat.text.toString()
////        Slong = Long.text.toString()
////        timezone = Timezone.text.toString()
////
////        lat = Slat.toDouble()
////        long = Long.toDouble()
////
////
////
////        lat = Lat.text.toString()
////        long = Long.text.toString()
////        timezone = Timezone.text.toString()
//
//        fun showForecasts(){
//
//        val retrofit = Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create()).build()
//        val retrofitAPI : ForecastAPI = retrofit.create(ForecastAPI::class.java)
//        val call : Call<DATACLASS> = retrofitAPI.getWeatherData(lat.toDouble(), long.toDouble(), timezone)
//
//        call.enqueue(object : Callback<DATACLASS>
//        {
//            override fun onResponse(call: Call<DATACLASS>, response: Response<DATACLASS>) {
//                if (!response.isSuccessful)
//                {
//
//
//                    Log.d("Test", "unsuccessful")
//
//
//
//                }else{
//                    Log.d("Test", "successful")
//                    forecastData=response.body() as DATACLASS
//
//
////                    runOnUiThread {
//
//                    organizeForecastData(forecastData)
////                    }
//
////                    GlobalScope.launch {
////
////                        val temp = async { mainBinding.hoursHighestTemp.text = " " + forecastData.hourly.temperature_2m[0].toString() + "°" }
////                        val RV = async {  organizeForecastData(forecastData, daydata) }
////                        temp.await()
////                        RV.await()
////                    }
//
//
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
//    }
//
//    fun organizeForecastData(forecastData: DATACLASS) {
//        for (i in 0..6) {
//
//            var tday = forecastData.daily.time[i]
//            tday = tday.replace("-", " / ")
//
//            var sunrise = forecastData.daily.sunrise[i]
//            sunrise = sunrise.substring(sunrise.indexOf("T") + 1)
//
//            var sunset = forecastData.daily.sunset[i]
//            sunset = sunset.substring(sunset.indexOf("T") + 1)
//
//            var hoursList: ArrayList<HourData> = ArrayList()
//
//            for (j in 0..23) {
//
//                var hour = forecastData.hourly.time[i * 24 + j]
//                hour = hour.substring(hour.indexOf("T") + 1)
//
//                var HourData = HourData(
//
//                    time = hour,
//                    temp = forecastData.hourly.temperature_2m[i * 24 + j],
//                    pressure = forecastData.hourly.pressure_msl[i * 24 + j],
//                    app_temp = forecastData.hourly.apparent_temperature[i * 24 + j],
//                    rain = forecastData.hourly.rain[i * 24 + j],
//                    humidity = forecastData.hourly.relativehumidity_2m[i * 24 + j],
//                    visibility = forecastData.hourly.visibility[i*24+j],
//                    windspeed = forecastData.hourly.windspeed_10m[i * 24 + j],
//                    winddirection = forecastData.hourly.winddirection_10m[i * 24 + j],
//                    weathercode = forecastData.hourly.weathercode[i * 24 + j]
//
//                )
//                hoursList.add(HourData)
//
//
//
//            }
//
//            var DayData = DayData(
//
//                time = tday,
//                temp_max = forecastData.daily.temperature_2m_max[i],
//                temp_min = forecastData.daily.temperature_2m_min[i],
//                apparent_temp_max = forecastData.daily.apparent_temperature_max[i],
//                apparent_temp_min = forecastData.daily.apparent_temperature_min[i],
//                sunrise = sunrise,
//                sunset = sunset,
//                precip_sum = forecastData.daily.precipitation_sum[i],
//                rain_sum = forecastData.daily.rain_sum[i],
//                wind_max = forecastData.daily.windspeed_10m_max[i],
//                weathercode = forecastData.daily.weathercode[i],
//
//                Hours = hoursList
//            )
//            daydata.add(DayData)
//            Dadapter.notifyDataSetChanged()
//        }
//
//        }




    override fun onStart() {
        super.onStart()

        auth = Firebase.auth
        val currentUser = auth.currentUser

        if (currentUser != null){
            val intent = Intent (applicationContext, WeatherActivity::class.java)
            startActivity(intent)
        }

    }
}

