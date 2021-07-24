package com.myapplication.presenter.ForecastDetails

import com.myapplication.model.information.ConsolidatedWeather

interface DetailsHome {
    interface Presenter{
        fun onSuccess(details: ConsolidatedWeather)
    }
}