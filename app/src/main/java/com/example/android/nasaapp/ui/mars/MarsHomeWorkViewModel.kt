package com.example.android.nasaapp.ui.mars

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nasaapp.BuildConfig
import com.example.android.nasaapp.repository.RetrofitImpl
import com.example.android.nasaapp.repository.mars_api.MarsPhotoDetails
import com.example.android.nasaapp.repository.mars_api.MarsResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarsHomeWorkViewModel(
    private val liveDataForViewToObserve: MutableLiveData<State> = MutableLiveData(),
    private val retrofitImpl: RetrofitImpl = RetrofitImpl()
) : ViewModel() {

    private var marsPhotos: ArrayList<MarsPhotoDetails>? = null

    enum class State {
        SUCCESS,
        LOADING,
        ERROR
    }

    fun getData(): LiveData<State> {
        return liveDataForViewToObserve
    }

    fun getMarsPhotos(): ArrayList<MarsPhotoDetails>? {
        return marsPhotos
    }

    fun sendServerRequest() {
        liveDataForViewToObserve.value = State.LOADING
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveDataForViewToObserve.value = State.ERROR
        } else {
            retrofitImpl.getRetrofitImpl().getMarsData(apiKey, 100).enqueue(callback)
        }
    }

    private val callback = object : Callback<MarsResponseData> {
        override fun onResponse(
            call: Call<MarsResponseData>,
            response: Response<MarsResponseData>
        ) {
            if (response.isSuccessful && response.body() != null) {
                marsPhotos = response.body()!!.photos
                liveDataForViewToObserve.value = State.SUCCESS
            } else {
                Log.e("ERROR", "callback error: in MarsHomeWorkViewModel ${call.request()}")
                liveDataForViewToObserve.value = State.ERROR
            }
        }

        override fun onFailure(call: Call<MarsResponseData>, t: Throwable) {
            Log.e("ERROR", "callback error: in MarsHomeWorkViewModel ${call.request()}")
            liveDataForViewToObserve.value = State.ERROR
        }
    }
}