package com.myapplication.presenter.forecastDetails

import com.myapplication.model.information.ConsolidatedWeather

interface DetailsHome {
    interface Presenter{
        fun onSuccess(details: ConsolidatedWeather)
    }
}