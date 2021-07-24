package com.myapplication.presenter

import com.myapplication.model.information.ConsolidatedWeather

interface ViewHome {
    interface View {
        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun showForecast(forecast: List<ConsolidatedWeather>)
    }

    interface Details {
        fun showForecastDetails(details: ConsolidatedWeather)
    }
}