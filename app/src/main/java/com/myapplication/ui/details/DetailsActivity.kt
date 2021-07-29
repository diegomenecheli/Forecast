package com.myapplication.ui.details

import com.myapplication.R
import com.myapplication.model.information.ConsolidatedWeather
import com.myapplication.model.information.data.ForecastDataSource
import com.myapplication.presenter.ViewHome
import com.myapplication.presenter.forecastDetails.DetailsPresenter
import com.myapplication.ui.AbstractActivity
import com.myapplication.utils.GetDayWeek.Companion.getWeekDayName
import com.myapplication.utils.LoadSvg
import com.myapplication.utils.LoadSvg.Companion.loadSvg
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AbstractActivity(), ViewHome.Details {

    private lateinit var details: ConsolidatedWeather
    private lateinit var presenter: DetailsPresenter

    override fun getLayout(): Int = R.layout.activity_details

    override fun onInject() {
        getForecastDetails()
        showForecastDetails(details)
        val dataSource = ForecastDataSource(this)
        presenter = DetailsPresenter(this, dataSource)
    }

    private fun getForecastDetails() {
        intent.extras?.let { forecastSent ->
            details = forecastSent.get("forecastDetails") as ConsolidatedWeather
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun showForecastDetails(details: ConsolidatedWeather) {
        humidity_percentage.text = "${details.humidity.toString()}%"
        windspeed_mph.text = "${Math.round(details.wind_speed)}mph"
        min_max.text = "${Math.round(details.min_temp)}°/${Math.round(details.max_temp)}°"
        day_week.text = getWeekDayName(details.applicable_date)
        forecast_wheather.loadSvg(LoadSvg.getUrl(details))
    }
}