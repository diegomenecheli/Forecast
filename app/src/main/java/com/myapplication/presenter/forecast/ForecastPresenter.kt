package com.myapplication.presenter.forecast

import com.myapplication.model.information.ForecastResponse
import com.myapplication.model.information.data.ForecastDataSource
import com.myapplication.presenter.ViewHome

class ForecastPresenter (
    val view: ViewHome.View,
    private val dataSource: ForecastDataSource
) : ForecastHome.Presenter {

    override fun requestAll() {
        this.view.showProgressBar()
        this.dataSource.getLocationInformation(this)
    }

    override fun onSuccess(forecastResponse: ForecastResponse) {
        this.view.showForecast(forecastResponse.consolidated_weather)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }

}