package com.example.android.nasaapp.repository

import com.example.android.nasaapp.repository.mars_api.MarsResponseData
import com.example.android.nasaapp.repository.picture_of_the_day_api.PictureOfTheDayResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*
This interface creates an API call to server NASA
 */

interface NasaAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey: String,
        @Query("date") date: String? = null
    ): Call<PictureOfTheDayResponseData>

    @GET("mars-photos/api/v1/rovers/curiosity/photos?")
    fun getMarsData(
        @Query("api_key") apiKey: String,
        @Query("sol") sol: Int?
    ): Call<MarsResponseData>
}