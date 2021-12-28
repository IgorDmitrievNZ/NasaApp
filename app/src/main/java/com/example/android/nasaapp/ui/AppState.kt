package com.example.android.nasaapp.ui

import com.example.android.nasaapp.repository.picture_of_the_day_api.PictureOfTheDayResponseData

sealed class AppState {
    data class Success(val responseData: PictureOfTheDayResponseData) : AppState()
    data class Loading(val progress: Int?) : AppState()
    data class Error(val error: Throwable) : AppState()
}