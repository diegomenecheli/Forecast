package com.myapplication.presenter

import com.myapplication.model.information.ConsolidatedWeather
import com.myapplication.model.search.LocationItem

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

    interface Settings{
        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun showAllLocations(locations: List<LocationItem>){}
    }
}