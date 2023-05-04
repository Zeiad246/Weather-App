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
import com.example.weather3.HourActivity
import com.example.weather3.R
import com.example.weather3.WeatherActivity
import com.example.weather3.WeatherData.DATACLASS
import com.example.weather3.WeatherData.Daily
import com.example.weather3.WeatherData.DayData
import com.example.weather3.WeatherData.HourData

class DaysAdapter(

    var daydata: ArrayList<DayData>,
//    var hourdata: ArrayList<HourData>,
    var context: Context

) : RecyclerView.Adapter<DaysAdapter.DaysViewHolder>() {


lateinit var inst : WeatherActivity
lateinit var varss : DATACLASS


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
        val adapter = HoursAdapter(holderParent.Hours, context)
        holder.childRecyclerView.adapter = adapter

        holder.parentCard.setOnClickListener {

            val intent = Intent(context, DayActivity::class.java)

            intent.apply {

                putExtra("daydate", daydata[position].time)
                putExtra("dayhightemp", daydata[position].temp_max)
                putExtra("daylowtemp", daydata[position].temp_min)
                putExtra("dayAppTempHigh", daydata[position].apparent_temp_max)
                putExtra("dayAppTempLow", daydata[position].apparent_temp_min)
                putExtra("daySunrise", daydata[position].sunrise)
                putExtra("daySunset", daydata[position].sunset)
                putExtra("dayPrecipSum", daydata[position].precip_sum)
                putExtra("dayWindMax", daydata[position].wind_max)
                putExtra("dayRainSum", daydata[position].rain_sum)

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


//        inst.organizeForecastData(varss)
//        val hours = mapOf(daydata[position])

//        val mapp = mutableMapOf<Int, ArrayList<HourData>>()
//
//        val v = ArrayList<HourData>()
//
//        mapp[position] = ArrayList<HourData>().

//        holder.childRecyclerView.setOnClickListener {
//
//
//
//
//            val intent = Intent(context, HourActivity::class.java)
//
//            for (i in daydata){
//                for (j in hourdata){
//
//                    intent.apply {
//
//                        putExtra("hourtime", daydata[i].Hours[position].time)
//                        putExtra("hourtemp", daydata[i].Hours[position].temp)
//                        putExtra("hourApptemp", daydata[i].Hours[position].app_temp)
//                        putExtra("hourHumid", daydata[i].Hours[position].humidity)
//                        putExtra("hourPressure", daydata[i].Hours[position].pressure)
//                        putExtra("hourWindSpeed", daydata[i].Hours[position].windspeed)
//                        putExtra("hourWindDir", daydata[i].Hours[position].winddirection)
//                        putExtra("hourVisib", daydata[i].Hours[position].visibility)
//                        putExtra("hourRain", daydata[i].Hours[position].rain)
//                    }
//                }
//
//            }
//
//
//
//            context.startActivity(intent)
//        }



//    fun nums(daysss: DayData){
//
////        when (position){
////            0 -> for(i in 0..23){
////                daydata[position].Hours[i]
////            }1 -> for(i in 24..47){
////                daydata[position].Hours[i]
////            }2 -> for(i in 48..71){
////                daydata[position].Hours[i]
////            }3 -> for(i in 72..95){
////                daydata[position].Hours[i]
////            }4 -> for(i in 96..119){
////                daydata[position].Hours[i]
////            }5 -> for(i in 120..133){
////                daydata[position].Hours[i]
////            }6 -> for(i in 134..23){
////                daydata[position].Hours[i]
////            }
////        }
//    }

    }

//    interface Map<K, out V>{
//
//    }

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