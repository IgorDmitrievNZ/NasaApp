package com.example.android.nasaapp.repository.mars_api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/*
This data class creates server response for mars details
 */

@Parcelize
data class MarsPhotoDetails(

    @field:SerializedName("img_src") val imgSrc: String?
) : Parcelable
