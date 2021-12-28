package com.example.android.nasaapp.repository.mars_api

import com.google.gson.annotations.SerializedName

data class MarsResponseData(
    @field:SerializedName("photos") val photos: ArrayList<MarsPhotoDetails>?
)
