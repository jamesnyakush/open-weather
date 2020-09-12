package com.jnyakush.weather.data.api

import com.jnyakush.weather.data.model.WeatherResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("weather")
    fun getWeather(
        @Query("q") city: String,
        @Query("appid") appid: String
    ): Call<WeatherResponseModel>
}