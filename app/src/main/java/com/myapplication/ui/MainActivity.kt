package com.myapplication.ui

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.R
import com.myapplication.adapter.MainAdapter
import com.myapplication.model.information.ConsolidatedWeather
import com.myapplication.model.information.data.ForecastDataSource
import com.myapplication.presenter.ViewHome
import com.myapplication.presenter.forecast.ForecastPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbstractActivity(), ViewHome.View {

    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var presenter: ForecastPresenter

    override fun getLayout(): Int = R.layout.activity_main

    override fun onInject() {
        val dataSource = ForecastDataSource(
            this
        )
        presenter = ForecastPresenter(this, dataSource)
        presenter.requestAll()
        configRecycler()
//        clickAdapter()
    }

    private fun configRecycler() {
        with(recycler_main) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
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

    override fun showForecast(forecast: List<ConsolidatedWeather>) {
        mainAdapter.differ.submitList(forecast.toList())
    }

}