package com.example.weather3.Search

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationAPI {

//    https://geocoding-api.open-meteo.com/

    @GET("v1/search?name=Berlin&count=1&language=en&format=json")
    fun getLocation(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
        @Query("timezone") timezone: String,
        @Query("name") city: String,
        @Query("country") country: String
    ) : Call<LocationDATA>
}