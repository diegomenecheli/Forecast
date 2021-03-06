package com.myapplication.ui.splash

import android.content.Intent
import com.myapplication.R
import com.myapplication.model.information.ForecastResponse
import com.myapplication.model.information.data.ForecastDataSource
import com.myapplication.presenter.ViewHome
import com.myapplication.presenter.forecast.ForecastPresenter
import com.myapplication.ui.AbstractActivity
import com.myapplication.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AbstractActivity(), ViewHome.View {

    private lateinit var forecastPresenter: ForecastPresenter

    override fun getLayout(): Int = R.layout.activity_splash_screen

    override fun onInject() {
        exitSplash()
    }

    private fun requestInformation() {
        val dataSource = ForecastDataSource(
            this
        )
        forecastPresenter = ForecastPresenter(this, dataSource)
        forecastPresenter.getFavorite()
    }

    private fun exitSplash() {
        iv_sun.alpha = 0f
        iv_sun.animate().setDuration(800).alpha(1f).withEndAction {
            requestInformation()
        }
    }

    override fun showForecast(forecast: ForecastResponse) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("forecast", forecast)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}