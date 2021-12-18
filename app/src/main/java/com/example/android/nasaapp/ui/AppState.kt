package com.example.android.nasaapp.ui

import com.example.android.nasaapp.repository.PictureOfTheDayResponseData

sealed class AppState {
    data class Success(val pictureOfTheDayResponseData: PictureOfTheDayResponseData) : AppState()
    data class Loading(val progress: Int?) : AppState()
    data class Error(val error: Throwable) : AppState()
}