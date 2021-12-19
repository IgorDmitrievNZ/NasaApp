package com.example.android.nasaapp.ui.picture

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nasaapp.BuildConfig
import com.example.android.nasaapp.repository.PictureOfTheDayResponseData
import com.example.android.nasaapp.repository.PictureOfTheDayRetrofitImpl
import com.example.android.nasaapp.ui.AppState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class PictureOfTheDayViewModel(
    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val liveDataForYesterdayViewToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val retrofitImpl: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl()
) : ViewModel() {
    fun getData(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun getYesterdayData(): LiveData<AppState> {
        return liveDataForYesterdayViewToObserve
    }

    fun sendServerRequest() {
        liveDataForViewToObserve.value = AppState.Loading(0)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveDataForViewToObserve.value = AppState.Error(Throwable("wrong key"))
        } else {
            retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey).enqueue(callbackToday)
            retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey, getDaysAgo(1))
                .enqueue(callbackYesterday)
        }
    }

    private val callbackToday = object : Callback<PictureOfTheDayResponseData> {
        override fun onResponse(
            call: Call<PictureOfTheDayResponseData>,
            response: Response<PictureOfTheDayResponseData>
        ) {
            if (response.isSuccessful && response.body() != null) {
                liveDataForViewToObserve.value = AppState.Success(response.body()!!)
            } else {
                Log.e("ERROR", "callback error: in PictureOfTheDayViewModel ${call.request()}")
                liveDataForViewToObserve.value = AppState.Error(Throwable())
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
            Log.e("ERROR", "callback error: in PictureOfTheDayViewModel ${call.request()}")
            liveDataForViewToObserve.value = AppState.Error(Throwable())
        }
    }

    private val callbackYesterday = object : Callback<PictureOfTheDayResponseData> {
        override fun onResponse(
            call: Call<PictureOfTheDayResponseData>,
            response: Response<PictureOfTheDayResponseData>
        ) {
            if (response.isSuccessful && response.body() != null) {
                liveDataForYesterdayViewToObserve.value = AppState.Success(response.body()!!)
            } else {
                Log.e("ERROR", "callback error: in PictureOfTheDayViewModel ${call.request()}")
                liveDataForYesterdayViewToObserve.value = AppState.Error(Throwable())
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
            Log.e("ERROR", "callback error: in PictureOfTheDayViewModel ${call.request()}")
            liveDataForYesterdayViewToObserve.value = AppState.Error(Throwable())
        }
    }

    private fun getDaysAgo(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val format = formatter.format(calendar.time)
        return format.toString()
    }
}