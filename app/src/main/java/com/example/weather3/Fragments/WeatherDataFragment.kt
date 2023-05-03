package com.example.weather3.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather3.Adapters.DaysAdapter
import com.example.weather3.WeatherData.DATACLASS
import com.example.weather3.WeatherData.DayData
import com.example.weather3.WeatherData.ForecastAPI
import com.example.weather3.WeatherData.HourData

import com.example.weather3.databinding.FragmentWeatherdataBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WeatherDataFragment : Fragment() {

    private var _binding: FragmentWeatherdataBinding? = null
    private val binding get()  = _binding!!




    val baseUrl = "https://api.open-meteo.com/"
    lateinit var forecastData : DATACLASS
    var daydata = ArrayList<DayData>()

    lateinit var Dadapter: DaysAdapter




    var lat: String? = ""
    var long: String? = ""
    var timezone: String? = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherdataBinding.inflate(inflater, container, false)
        val view = binding.root

//        binding.DayCardsList.layoutManager = LinearLayoutManager(this.context)
//        Dadapter = DaysAdapter(daydata)
//        binding.DayCardsList.adapter = Dadapter


        lat = arguments?.getString("lat")!!
        long = arguments?.getString("long")!!
        timezone= arguments?.getString("timezone")!!

binding.apply {
    latitudeBox2.text = lat

    longitudeBox2.text = long
    timezoneBox2.text = timezone
}



//        showForecasts()


        return view
    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}