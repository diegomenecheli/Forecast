package com.myapplication.model.information.data

import android.content.Context
import com.myapplication.network.RetrofitInstance
import com.myapplication.presenter.forecast.ForecastHome
import kotlinx.coroutines.*

class ForecastDataSource(context: Context) {

    fun getLocationInformation(callback: ForecastHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getLocationInformation(44418)
            if (response.isSuccessful) {
                response.body()?.let { forecastResponse ->
                    callback.onSuccess(forecastResponse)
                }
                callback.onComplete()
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }
}