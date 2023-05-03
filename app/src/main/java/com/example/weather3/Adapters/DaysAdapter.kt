package com.example.weather3.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather3.DayActivity
import com.example.weather3.R
import com.example.weather3.WeatherData.DayData
import com.example.weather3.WeatherData.HourData

class DaysAdapter(

    var daydata: ArrayList<DayData>,
    var context: Context

) : RecyclerView.Adapter<DaysAdapter.DaysViewHolder>() {


    var hourdata = ArrayList<HourData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.day_cards,
                parent,
                false
            )
        return DaysViewHolder(view)
    }

    override fun getItemCount(): Int {
//        return DayDate.size
        return daydata.size
    }

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        val holderParent = daydata[position]


        holder.daydate.text = holderParent.time
        holder.dayhightemp.text = " " + holderParent.temp_max.toString() + "°C"
        holder.daylowtemp.text = " " + holderParent.temp_min.toString() + "°C"
        holder.daySunrise.text = holderParent.sunrise
        holder.daySunset.text = holderParent.sunset
        holder.dayAppTempHigh.text = holderParent.apparent_temp_max.toString() + "°C"
        holder.dayAppTempLow.text = holderParent.apparent_temp_min.toString() + "°C"
        holder.dayPrecipSum.text = holderParent.precip_sum.toString() + "hPa"
        holder.dayRainSum.text = holderParent.rain_sum.toString() + "mm"
        holder.dayWindMax.text = holderParent.wind_max.toString() + "km/h"


        holder.childRecyclerView.setHasFixedSize(true)
        holder.childRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = HoursAdapter(holderParent.Hours)
        holder.childRecyclerView.adapter = adapter

        holder.parentCard.setOnClickListener {

            val intent = Intent(context, DayActivity::class.java)

            intent.apply {
                putExtra("daydate", daydata[position].time)

            }

            context.startActivity(intent)

//            var intent = Intent(context, DayActivity::class.java)
//
//            intent.apply {
//               // putExtra("daydate", holderParent.time)
//                putExtra("dayhightemp", holderParent.temp_max)
//
//
//
//            }
//            context.startActivity(intent)


//            val bundle = Bundle()
//            bundle.putString("dayhightemp", "H: " + holderParent.temp_max.toString() + "°")
//            bundle.putString("daylowtemp", "H: " + holderParent.temp_min.toString() + "°")
//
//            val fragment = DaysFragment()
//            fragment.arguments = bundle




        }




    }

    inner class DaysViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

        val childRecyclerView : RecyclerView = itemView.findViewById(R.id.childrecyclerview)

        var parentCard: CardView = itemView.findViewById(R.id.DayCardView)
        var daydate: TextView = itemView.findViewById(R.id.dayDate)
        var dayhightemp: TextView = itemView.findViewById(R.id.dayHighTemp)
        var daylowtemp: TextView = itemView.findViewById(R.id.dayLowTemp)
        var daySunrise : TextView = itemView.findViewById(R.id.daySunrise)
        var daySunset : TextView = itemView.findViewById(R.id.daySunset)
        var dayAppTempHigh : TextView = itemView.findViewById(R.id.dayApparentHighTemp)
        var dayAppTempLow : TextView = itemView.findViewById(R.id.dayApparentLowTemp)
        var dayPrecipSum : TextView = itemView.findViewById(R.id.dayPresepitationBox)
        var dayRainSum : TextView = itemView.findViewById(R.id.dayRainBox)
        var dayWindMax : TextView = itemView.findViewById(R.id.dayWindMaxBox)


    }
}