package com.myapplication.presenter.forecast

import com.myapplication.model.information.ForecastResponse
import com.myapplication.model.information.data.ForecastDataSource
import com.myapplication.presenter.ViewHome

class ForecastPresenter(
    val view: ViewHome.View,
    private val dataSource: ForecastDataSource
) : ForecastHome.Presenter {


    override fun onSuccess(forecastResponse: ForecastResponse) {
        this.view.showForecast(forecastResponse)
    }

    override fun getFavorite() {
        this.view.showProgressBar()
        this.dataSource.getFavoriteLocation(this)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }

}