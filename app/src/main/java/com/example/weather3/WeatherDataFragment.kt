package com.example.weather3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather3.WeatherData.DATACLASS
import com.example.weather3.WeatherData.DayData

import com.example.weather3.databinding.FragmentWeatherdataBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WeatherDataFragment : Fragment() {

    private var _binding: FragmentWeatherdataBinding? = null
    private val binding get()  = _binding!!


    lateinit var recyclerView: RecyclerView
    lateinit var Dadapter: DaysAdapter

    lateinit var forecastData : DATACLASS
    var daydata = ArrayList<DayData>()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherdataBinding.inflate(inflater, container, false)
        val view = binding.root



//        binding.DayCardsList.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
//        Dadapter = DaysAdapter(daydata)
//        binding.DayCardsList.adapter = Dadapter
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.open-meteo.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val retrofitAPI : ForecastAPI = retrofit.create(ForecastAPI::class.java)
//        val call : Call<DATACLASS> = retrofitAPI.getWeatherData(52.01, 51.00, "Africa/Cairo")
//
//            call.enqueue(object : Callback<DATACLASS>
//            {
//                override fun onResponse(call: Call<DATACLASS>, response: Response<DATACLASS>) {
//                    if (!response.isSuccessful)
//                    {
//
//
//
//
//                    }else{
//                        forecastData=response.body() as DATACLASS
//
//
////                        organizeForecastData(forecastData, daydata)
//
//
//
//                    }
//
//                }
//
//                override fun onFailure(call: Call<DATACLASS>, t: Throwable) {
//                    t.printStackTrace()
//                    Log.e("Error", t.message.toString())
//                }
//
//            })


        return view
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}