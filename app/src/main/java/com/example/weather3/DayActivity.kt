package com.example.weather3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weather3.databinding.ActivityDayBinding

class DayActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityDayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day)

        mainBinding = ActivityDayBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)


        val time : String? = intent.getStringExtra("daydate")
        val hightemp : Double = intent.getDoubleExtra("dayhightemp", 0.0)
        val lowtemp : Double = intent.getDoubleExtra("daylowtemp", 0.0)
        val apphightemp : Double = intent.getDoubleExtra("dayAppTempHigh", 0.0)
        val applowtemp : Double = intent.getDoubleExtra("dayAppTempLow", 0.0)
        val sunrise : String? = intent.getStringExtra("daySunrise")
        val sunset : String? = intent.getStringExtra("daySunset")
        val precip : Double = intent.getDoubleExtra("dayPrecipSum", 0.0)
        val windmax : Double = intent.getDoubleExtra("dayWindMax", 0.0)
        val rain: Double = intent.getDoubleExtra("dayRainSum", 0.0)


        mainBinding.apply {

            hoursTime.text = time
            hoursHighestTemp.text = hightemp.toString()
            hoursLowestTemp.text = lowtemp.toString()
            hoursHighApparent.text = apphightemp.toString()
            hoursLowApparent.text = applowtemp.toString()
            sunriseBox.text = sunrise
            sunsetBox.text = sunset
            hoursPresipSumBox.text = precip.toString()
            hoursWindSpeedMaxBox.text = windmax.toString()
            hoursRainSumBox.text = rain.toString()

        }

    }
}