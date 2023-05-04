package com.example.weather3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weather3.databinding.ActivityDayBinding
import com.example.weather3.databinding.ActivityHourBinding

class HourActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityHourBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityHourBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        val time : String? = intent.getStringExtra("hourtime")
        val temp : Double = intent.getDoubleExtra("hourtemp", 0.0)
        val apptemp : Double = intent.getDoubleExtra("hourApptemp", 0.0)
        val humid : Int = intent.getIntExtra("hourHumid", 0)
        val pressu : Double = intent.getDoubleExtra("hourPressure", 0.0)
        val windspeed : Double = intent.getDoubleExtra("hourWindSpeed", 0.0)
        val winddir : Int = intent.getIntExtra("hourWindDir", 0)
        val visib : Double = intent.getDoubleExtra("hourVisib", 0.0)
        val rain : Double = intent.getDoubleExtra("hourRain", 0.0)


        mainBinding.apply {

            hoursTime.text = time
            hoursTemp.text = temp.toString()
            hoursAppTemp.text = apptemp.toString()
            hoursHumidBox.text = humid.toString()
            hoursPressureBox.text = pressu.toString()
            hoursWindSpeed.text = windspeed.toString()
            hoursWindDir.text = winddir.toString()
            hoursVisibilityBox.text = visib.toString()
            hoursRain.text = rain.toString()

        }

    }
}