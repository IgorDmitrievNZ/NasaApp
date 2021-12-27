package com.example.android.nasaapp.repository.mars_api

import com.google.gson.annotations.SerializedName

/*
This data class creates server response for mars details
 */

data class MarsPhotoDetails(

    @field:SerializedName("img_src") val imgSrc: String?
)
