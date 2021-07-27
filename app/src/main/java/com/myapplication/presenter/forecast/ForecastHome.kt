package com.myapplication.presenter.forecast

import com.myapplication.model.information.ForecastResponse

interface ForecastHome {
    interface Presenter {
        fun getFavorite()
        fun onSuccess(forecastResponse: ForecastResponse)
        fun onError(message: String)
        fun onComplete()
    }
}