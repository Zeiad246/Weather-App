package com.example.weather3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather3.WeatherData.HourData

class HoursAdapter (

    var hourlydata: ArrayList<HourData>

    ) : RecyclerView.Adapter<HoursAdapter.HoursViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursAdapter.HoursViewHolder {
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


    override fun onBindViewHolder(holder: HoursAdapter.HoursViewHolder, position: Int) {

        val holderChild = hourlydata[position]

        holder.hourtemp.text = holderChild.temp.toString() + "°"
        holder.hourtime.text = holderChild.time
    }



    inner class HoursViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        var hourtemp: TextView = itemView.findViewById(R.id.hourTemp)
        var hourtime: TextView = itemView.findViewById(R.id.hourTime)


    }

}