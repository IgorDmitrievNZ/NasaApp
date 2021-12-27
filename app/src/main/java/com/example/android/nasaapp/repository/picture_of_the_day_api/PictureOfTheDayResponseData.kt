package com.example.android.nasaapp.repository.picture_of_the_day_api

import com.google.gson.annotations.SerializedName

/*
This data class creates server response for picture of the day data
 */

data class PictureOfTheDayResponseData(

    // "@field:SerializedName" for custom naming of variables
    // EXAMPLE: "val media_type: String?" is the way to write a code without "@field:SerializedName"
    @field:SerializedName("copyright") val copyright: String?,
    @field:SerializedName("date") val date: String?,
    @field:SerializedName("explanation") val explanation: String?,
    @field:SerializedName("media_type") val mediaType: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("url") val url: String?,
    @field:SerializedName("hdurl") val hdURL: String?,
    @field:SerializedName("service_version") val serviceVersion: String?
)
