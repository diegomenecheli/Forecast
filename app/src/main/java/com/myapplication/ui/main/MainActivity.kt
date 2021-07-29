package com.myapplication.ui.main

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.R
import com.myapplication.adapter.MainAdapter
import com.myapplication.model.information.ForecastResponse
import com.myapplication.model.information.data.ForecastDataSource
import com.myapplication.presenter.ViewHome
import com.myapplication.presenter.forecast.ForecastPresenter
import com.myapplication.ui.AbstractActivity
import com.myapplication.ui.details.DetailsActivity
import com.myapplication.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AbstractActivity(), ViewHome.View, ViewHome.Settings {

    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var details: ForecastResponse
    private lateinit var forecastPresenter: ForecastPresenter

    override fun getLayout(): Int = R.layout.activity_main

    override fun onInject() {
        getForecastDetails()
        configRecycler()
        clickAdapter()
    }

    private fun requestInformation() {
        val dataSource = ForecastDataSource(
            this
        )
        forecastPresenter = ForecastPresenter(this, dataSource)
        forecastPresenter.getFavorite()
    }


    private fun configRecycler() {
        with(recycler_main) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        requestInformation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Intent(this, SettingsActivity::class.java).apply {
            startActivity(this)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showProgressBar() {
        main_loading.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        main_loading.visibility = View.INVISIBLE
    }

    override fun showForecast(forecast: ForecastResponse) {
        location_name.text = forecast.title
        mainAdapter.differ.submitList(forecast.consolidated_weather)
    }

    private fun getForecastDetails() {
        intent.extras?.let { forecast ->
            details = forecast.get("forecast") as ForecastResponse
            location_name.text = details.title
            mainAdapter.differ.submitList(details.consolidated_weather)
        }
    }

    private fun clickAdapter() {
        mainAdapter.setOnClickListener { forecast ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("forecast", forecast)
            startActivity(intent)
        }
    }

}