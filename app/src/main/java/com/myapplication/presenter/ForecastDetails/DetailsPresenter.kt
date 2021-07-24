package com.myapplication.presenter.ForecastDetails

import com.myapplication.model.information.ConsolidatedWeather
import com.myapplication.model.information.data.ForecastDataSource
import com.myapplication.presenter.ViewHome

class DetailsPresenter(
    val view: ViewHome.Details,
    private val dataSource: ForecastDataSource
) : DetailsHome.Presenter {
    override fun onSuccess(forecast: ConsolidatedWeather) {
        this.view.showForecastDetails(forecast)
    }

}