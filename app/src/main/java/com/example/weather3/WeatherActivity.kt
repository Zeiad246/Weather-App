package com.example.weather3

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Geocoder
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather3.Adapters.DaysAdapter
import com.example.weather3.Search.LocationAPI
import com.example.weather3.Search.LocationDATA
import com.example.weather3.WeatherData.DATACLASS
import com.example.weather3.WeatherData.DayData
import com.example.weather3.WeatherData.ForecastAPI
import com.example.weather3.WeatherData.HourData
import com.example.weather3.databinding.ActivityWeatherBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class WeatherActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityWeatherBinding
    lateinit var sharedPref : SharedPreferences

    val baseUrl = "https://api.open-meteo.com/"

    val baseUrl2 = "https://geocoding-api.open-meteo.com/"


    lateinit var forecastData : DATACLASS
    var daydata = ArrayList<DayData>()

    lateinit var Dadapter: DaysAdapter

    lateinit var locationData : LocationDATA

    var Latii: Double = 0.0
    var Longii: Double = 0.0
    var Timezoneii: String = ""
    var Cityii: String = ""
    var Countryii: String = ""


    var CHANNEL_ID = "1"
    override fun onPause() {
        super.onPause()
        saveData()
    }
    override fun onResume() {
        super.onResume()
        retrieveData()
        showForecasts()
    }

    fun saveData(){
        sharedPref=this.getSharedPreferences("saveData", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.apply{
            putString("lat cord", Latii.toString())
            putString("long cord",Longii.toString())
            putString("timezone", Timezoneii)
            putString("city name", Cityii)
            putString("country name", Countryii)

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

        Latii = lat.toString().toDouble()
        Longii = long.toString().toDouble()
        Timezoneii = timezone.toString()
        Cityii = city.toString()
        Countryii = country.toString()

    }



    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityWeatherBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        val Lat : Double = intent.getDoubleExtra("lati", 31.00)
        val Long : Double = intent.getDoubleExtra("longi", 30.00)
        val Timezone : String = intent.getStringExtra("timezonei").toString()
        val City : String = intent.getStringExtra("cityi").toString()
        val Country : String = intent.getStringExtra("countryi").toString()

        Latii = Lat
        Longii = Long
        Timezoneii = Timezone
        Cityii = City
        Countryii = Country



        //        val firstFragment = SignInFragment()
//        supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder, firstFragment)
//            .commit()


        mainBinding.DayCardsList.layoutManager = LinearLayoutManager(this@WeatherActivity, LinearLayoutManager.VERTICAL, false)
        Dadapter = DaysAdapter(daydata, this@WeatherActivity)
        mainBinding.DayCardsList.adapter = Dadapter



        mainBinding.OKButton2.setOnClickListener {
            val intent = Intent(applicationContext, LocationActivity::class.java)
            startActivity(intent)
        }

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

//        mainBinding.apply {
//
//            latitudeBox2.text = lat.toEditable()
//            longitudeBox2.text = long.toEditable()
//            timezoneBox2.text = timezone.toEditable()
//            CityBox.text = city.toEditable()
//            CountryBox.text = country.toEditable()
//
//        }
//
//
//
//
//        mainBinding.OKButton.setOnClickListener {
//
//            lat = mainBinding.latitudeBox2.text.toString()
//            long = mainBinding.longitudeBox2.text.toString()
//            timezone = mainBinding.timezoneBox2.text.toString()
//            city = mainBinding.CityBox.text.toString()
//            country = mainBinding.CountryBox.text.toString()
//
//            showForecasts()
//        }
//
//        mainBinding.SignOutButton.setOnClickListener {
//
//            Firebase.auth.signOut()
//            val intent = Intent(applicationContext, MainActivity::class.java)
//            startActivity(intent)
//
//        }




//        mainBinding.OpenButton.setOnClickListener {
//
//            showDialog()
//
//        }

        showForecasts()



    }




//    override fun passData(lat: String, long: String, timezone: String) {
//        val bundle = Bundle()
//        bundle.putString("lat", lat)
//        bundle.putString("long", long)
//        bundle.putString("timezone", timezone)
//        val transaction = this.supportFragmentManager.beginTransaction()
//        val fragmentTwo = WeatherDataFragment()
//        fragmentTwo.arguments = bundle
//        transaction.replace(R.id.mainlayout, fragmentTwo)
//        transaction.addToBackStack(null)
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//        transaction.commit()
//    }

//    fun EditText.getDouble(): Double = try {
//        text.toString().toDouble()
//    } catch (e: NumberFormatException) {
//        e.printStackTrace()
//        0.0
//    }

//    fun EditText.toDouble() = toString().toDouble()
//
//    fun test() {
//
//    }
//
//
//    val lat : String = "52.00"
//    val long : String = "52.00"
//    val timezone : String = "Africa/Cairo"


//    fun showDialog() {
//
//    }

//        val dialog = Dialog(this@MainApp)
//
//        val Lat : EditText = dialog.findViewById(R.id.latitudeBox)
//        val Long : EditText = dialog.findViewById(R.id.longitudeBox)
//        val Timezone : EditText = dialog.findViewById(R.id.timezoneBox)
//
//        dialog.requestWindowFeature(
//            Window.FEATURE_NO_TITLE
//        )
//
//        dialog.setCancelable(false)
//        dialog.setContentView(R.layout.location_dialog)
//        dialog.window!!.attributes.windowAnimations = R.style.dialoganimation
//
//
//        dialog.window!!.setGravity(Gravity.TOP)
//
//        dialog.show()
//
//        var OkButton = dialog.findViewById<Button>(R.id.cancel_button)
//
//        OkButton.setOnClickListener {
//
//            dialog.dismiss()
//
//            Lat.setText(Lat.text.toString())
//            Long.setText(Long.text.toString())
//            Timezone.setText(Timezone.text.toString())
//
//
//            showForecasts(Lat.text.toString(), Long.text.toString(), Timezone.text.toString())
//
//
//
//
//        Slat = Lat.text.toString()
//        Slong = Long.text.toString()
//        timezone = Timezone.text.toString()
//
//        lat = Slat.toDouble()
//        long = Long.toDouble()
//
//
//
//        lat = Lat.text.toString()
//        long = Long.text.toString()
//        timezone = Timezone.text.toString()

    fun showForecasts(){


        val retrofit = Builder()
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

                    organizeForecastData(forecastData)



                    for (i in 0..167){

                        if (forecastData.hourly.rain[i] > 0){
                            rainNotification()
                        }

                        if (forecastData.hourly.visibility[i] <= 20){
                            VisibilityNotification()
                        }
                    }


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

        val retrofit2 = Builder()
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



                    if (locationData.results[0].latitude.toString() != Latii.toString()){

                        Latii  = locationData.results[0].latitude
                        Longii = locationData.results[0].longitude
                        Timezoneii = locationData.results[0].timezone
                        Cityii = locationData.results[0].name
                        Countryii = locationData.results[0].country



                    }else if (locationData.results[0].longitude.toString() != Longii.toString()){

                        Latii = locationData.results[0].latitude
                        Longii = locationData.results[0].longitude
                        Timezoneii = locationData.results[0].timezone
                        Cityii = locationData.results[0].name
                        Countryii = locationData.results[0].country

                    }else if (locationData.results[0].timezone != Timezoneii){

                        Latii = locationData.results[0].latitude
                        Longii = locationData.results[0].longitude
                        Timezoneii = locationData.results[0].timezone
                        Cityii = locationData.results[0].name
                        Countryii = locationData.results[0].country

                    }else if (locationData.results[0].name != Cityii){

                        Latii = locationData.results[0].latitude
                        Longii = locationData.results[0].longitude
                        Timezoneii = locationData.results[0].timezone
                        Cityii = locationData.results[0].name
                        Countryii = locationData.results[0].country

                    }else if (locationData.results[0].country != Countryii){

                        Latii = locationData.results[0].latitude
                        Longii = locationData.results[0].longitude
                        Timezoneii = locationData.results[0].timezone
                        Cityii = locationData.results[0].name
                        Countryii = locationData.results[0].country

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


    fun rainNotification(){

        val builder = NotificationCompat.Builder(this@WeatherActivity, CHANNEL_ID)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, "1", NotificationManager.IMPORTANCE_DEFAULT )

            val manager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            manager.createNotificationChannel(channel)
            builder.setSmallIcon(R.drawable.ic_rainshower)
                .setContentTitle("Weather")
                .setContentText("Raining soon!")

        }else{
            builder.setSmallIcon(R.drawable.ic_rainshower)
                .setContentTitle("Notification Title")
                .setContentText("This is notification text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }

        val notificationManagerCompat = NotificationManagerCompat.from(this@WeatherActivity)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManagerCompat.notify(1, builder.build())
    }

    fun VisibilityNotification(){

        val builder = NotificationCompat.Builder(this@WeatherActivity, CHANNEL_ID)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, "1", NotificationManager.IMPORTANCE_DEFAULT )

            val manager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            manager.createNotificationChannel(channel)
            builder.setSmallIcon(R.drawable.ic_rainshower)
                .setContentTitle("Weather")
                .setContentText("Temperature is below 20°C")

        }else{
            builder.setSmallIcon(R.drawable.ic_rainshower)
                .setContentTitle("Notification Title")
                .setContentText("This is notification text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }

        val notificationManagerCompat = NotificationManagerCompat.from(this@WeatherActivity)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManagerCompat.notify(1, builder.build())
    }

    fun organizeForecastData(forecastData: DATACLASS) {
        for (i in 0..6) {

            var tday = forecastData.daily.time[i]
            tday = tday.replace("-", " / ")

            var sunrise = forecastData.daily.sunrise[i]
            sunrise = sunrise.substring(sunrise.indexOf("T") + 1)

            var sunset = forecastData.daily.sunset[i]
            sunset = sunset.substring(sunset.indexOf("T") + 1)

            var hoursList: ArrayList<HourData> = ArrayList()

            for (j in 0..23) {

                var hour = forecastData.hourly.time[i * 24 + j]
                hour = hour.substring(hour.indexOf("T") + 1)

                var HourData = HourData(

                    time = hour,
                    temp = forecastData.hourly.temperature_2m[i * 24 + j],
                    pressure = forecastData.hourly.pressure_msl[i * 24 + j],
                    app_temp = forecastData.hourly.apparent_temperature[i * 24 + j],
                    rain = forecastData.hourly.rain[i * 24 + j],
                    humidity = forecastData.hourly.relativehumidity_2m[i * 24 + j],
                    visibility = forecastData.hourly.visibility[i*24+j],
                    windspeed = forecastData.hourly.windspeed_10m[i * 24 + j],
                    winddirection = forecastData.hourly.winddirection_10m[i * 24 + j],
                    weathercode = forecastData.hourly.weathercode[i * 24 + j]

                )
                hoursList.add(HourData)



            }

            var DayData = DayData(

                time = tday,
                temp_max = forecastData.daily.temperature_2m_max[i],
                temp_min = forecastData.daily.temperature_2m_min[i],
                apparent_temp_max = forecastData.daily.apparent_temperature_max[i],
                apparent_temp_min = forecastData.daily.apparent_temperature_min[i],
                sunrise = sunrise,
                sunset = sunset,
                precip_sum = forecastData.daily.precipitation_sum[i],
                rain_sum = forecastData.daily.rain_sum[i],
                wind_max = forecastData.daily.windspeed_10m_max[i],
                weathercode = forecastData.daily.weathercode[i],

                Hours = hoursList
            )
            daydata.add(DayData)
            Dadapter.notifyDataSetChanged()
        }

    }

//    private fun getCityName(lat: Double,long: Double):String{
//        val cityName: String?
//        val geoCoder = Geocoder(this, Locale.getDefault())
//        val Adress = geoCoder.getFromLocation(lat,long,3)
//
////        cityName = Adress[0].adminArea
////        binding.txtCity.text = cityName
//        return cityName
//    }
}