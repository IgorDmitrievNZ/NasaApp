package com.example.android.nasaapp.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*
This interface creates an API call to server
 */

interface PictureOfTheDayAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<PictureOfTheDayResponseData>
}