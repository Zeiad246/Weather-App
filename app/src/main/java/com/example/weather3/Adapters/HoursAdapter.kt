package com.example.weather3.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather3.DayActivity
import com.example.weather3.HourActivity
import com.example.weather3.R
import com.example.weather3.WeatherData.DayData
import com.example.weather3.WeatherData.HourData

class HoursAdapter (

//    var days : ArrayList<DayData>,
    var hourlydata: ArrayList<HourData>,
    var context: Context

    ) : RecyclerView.Adapter<HoursAdapter.HoursViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.hour_cards,
            parent,
            false
        )
        return HoursViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hourlydata.size
    }


    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {

        val holderChild = hourlydata[position]



//        val da = days

        holder.hourtime.text = holderChild.time
        holder.hourtemp.text = holderChild.temp.toString() + "°C"
        holder.hourAppTemp.text = holderChild.app_temp.toString() + "°C"
        holder.hourHumid.text = "H:  " + holderChild.humidity.toString() + "%"
        holder.hourPressure.text = "P: " + holderChild.pressure.toString() + "hPa"
        holder.hourWindSpeed.text = "WS: " + holderChild.windspeed.toString() + "km/h"
        holder.hourWindDir.text = "WD: " + holderChild.winddirection.toString() + "°"
        holder.hourVisib.text = "V:  " + holderChild.visibility.toString() + "m"
        holder.hourRain.text = "R:  " + holderChild.rain.toString() + "mm"

//        holder.childRecyclerView.setOnClickListener {
//
//            val intent = Intent(context, HourActivity::class.java)
//
//            intent.apply {
//
//                for (i in 0..6){
//                    for (i in 0..23){
//
//                putExtra("hourtime", hourlydata[position].time)
//                putExtra("hourtemp", hourlydata[position].temp)
//                putExtra("hourApptemp", hourlydata[position].app_temp)
//                putExtra("hourHumid", hourlydata[position].humidity)
//                putExtra("hourPressure", hourlydata[position].pressure)
//                putExtra("hourWindSpeed", hourlydata[position].windspeed)
//                putExtra("hourWindDir", hourlydata[position].winddirection)
//                putExtra("hourVisib", hourlydata[position].visibility)
//                putExtra("hourRain", hourlydata[position].rain)
//            }
//
//                }
//            }
//
//            context.startActivity(intent)
//        }
//
    }



    inner class HoursViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

//        val childRecyclerView : RecyclerView = itemView.findViewById(R.id.childrecyclerview)

        var hourtime: TextView = itemView.findViewById(R.id.hourTime)
        var hourtemp: TextView = itemView.findViewById(R.id.hourTemp)
        var hourAppTemp: TextView = itemView.findViewById(R.id.hourApparentTemp)
        var hourHumid: TextView = itemView.findViewById(R.id.hourHumidityBox)
        var hourPressure: TextView = itemView.findViewById(R.id.hoursPressureBox)
        var hourWindSpeed: TextView = itemView.findViewById(R.id.hourWindSpeedBox)
        var hourWindDir: TextView = itemView.findViewById(R.id.hourWindDirectionBox)
        var hourVisib: TextView = itemView.findViewById(R.id.hourVisibilityBox)
        var hourRain: TextView = itemView.findViewById(R.id.hourRainBox)

    }

}