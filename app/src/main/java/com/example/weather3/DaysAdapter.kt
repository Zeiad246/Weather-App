package com.example.weather3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather3.WeatherData.DayData
import com.example.weather3.WeatherData.HourData

class DaysAdapter(

    var daydata: ArrayList<DayData>

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
        holder.dayhightemp.text = holderParent.temp_max.toString() + "°"
        holder.daylowtemp.text = holderParent.temp_min.toString() + "°"


        holder.childRecyclerView.setHasFixedSize(true)
        holder.childRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = HoursAdapter(holderParent.Hours)
        holder.childRecyclerView.adapter = adapter

        holder.parentCard.setOnClickListener {

        }




    }

    inner class DaysViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

        val childRecyclerView : RecyclerView = itemView.findViewById(R.id.childrecyclerview)

        var parentCard: CardView = itemView.findViewById(R.id.DayCardView)
        var daydate: TextView = itemView.findViewById(R.id.dayDate)
        var dayhightemp: TextView = itemView.findViewById(R.id.dayHighTemp)
        var daylowtemp: TextView = itemView.findViewById(R.id.dayLowTemp)



    }
}