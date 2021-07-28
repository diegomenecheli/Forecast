package com.myapplication.presenter

import com.myapplication.model.information.ConsolidatedWeather
import com.myapplication.model.information.ForecastResponse
import com.myapplication.model.search.LocationItem

interface ViewHome {
    interface View {
        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun showForecast(forecast: ForecastResponse)
    }

    interface Details {
        fun showForecastDetails(details: ConsolidatedWeather)
    }

    interface Settings {
        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun showAllLocations(locations: List<LocationItem>) {}
    }
}