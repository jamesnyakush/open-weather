package com.jnyakush.weather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.jnyakush.weather.R
import com.jnyakush.weather.data.api.ApiClient
import com.jnyakush.weather.data.api.ApiInterface
import com.jnyakush.weather.data.model.Main
import com.jnyakush.weather.data.model.WeatherResponseModel
import kotlinx.android.synthetic.main.weather_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_activity)


        fetchWeather()
    }

    private fun fetchWeather() {

        val apiClient = ApiClient.buildService(ApiInterface::class.java)
        val call = apiClient.getWeather("Nairobi,ke", "08aa22bc0a3895c771f7f91ffa36ad96")

        call.enqueue(object : Callback<WeatherResponseModel> {
            override fun onFailure(call: Call<WeatherResponseModel>, t: Throwable) {
                Log.d("Weather", t.message.toString())
            }

            override fun onResponse(
                call: Call<WeatherResponseModel>,
                response: Response<WeatherResponseModel>
            ) {
                if (response.isSuccessful) {
                    //Log.d("Weather", response.body().toString())

                    humidity.text = response.body()?.main?.humidity.toString()
                    pressure.text = response.body()?.main?.pressure.toString()
                    temp.text = response.body()?.main?.temp.toString()
                    temp_max.text = response.body()?.main?.temp_max.toString()
                    temp_min.text = response.body()?.main?.temp_min.toString()

                    /*
                         Toast.makeText(
                            this@WeatherActivity,
                            response.body().toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    */
                } else {
                    Log.d("Weather", response.errorBody().toString())
                }
            }

        })

    }
}