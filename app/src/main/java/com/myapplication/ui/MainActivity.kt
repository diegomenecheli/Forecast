package com.myapplication.ui

import android.widget.Toast
import com.myapplication.R
import com.myapplication.model.information.ConsolidatedWeather
import com.myapplication.model.information.data.ForecastDataSource
import com.myapplication.presenter.ViewHome
import com.myapplication.presenter.forecast.ForecastPresenter

class MainActivity : AbstractActivity(), ViewHome.View {


    private lateinit var presenter: ForecastPresenter

    override fun getLayout(): Int = R.layout.activity_main

    override fun onInject() {
        val dataSource = ForecastDataSource(
            this
        )
        presenter = ForecastPresenter(this, dataSource)
        presenter.requestAll()
    }


    override fun showProgressBar() {
        //TODO
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        //TODO
    }

    override fun showForecast(forecast: List<ConsolidatedWeather>) {
        //TODO
    }

}