package com.myapplication.ui.settings

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.R
import com.myapplication.adapter.ButtonsAdapter
import com.myapplication.model.search.LocationItem
import com.myapplication.model.search.data.LocationsDataSource
import com.myapplication.presenter.ViewHome
import com.myapplication.presenter.settings.SettingsPresenter
import com.myapplication.ui.AbstractActivity
import com.myapplication.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AbstractActivity(), ViewHome.Settings {

    private val buttonsAdapter by lazy {
        ButtonsAdapter()
    }

    private lateinit var presenter: SettingsPresenter


    override fun getLayout(): Int = R.layout.activity_settings

    override fun onInject() {
        val dataSource = LocationsDataSource(
            this
        )
        presenter = SettingsPresenter(this, dataSource)
        presenter.getAll()
        configRecycler()
        clickAdapter()
    }

    private fun configRecycler() {
        with(recycler_buttons) {
            adapter = buttonsAdapter
            layoutManager = LinearLayoutManager(this@SettingsActivity)
        }
    }

    private fun clickAdapter() {
        buttonsAdapter.setOnClickListener { location ->
            presenter.saveFavorite(location)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("forecast", location)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }

    override fun showProgressBar() {
        settings_loading.visibility = View.INVISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        settings_loading.visibility = View.INVISIBLE
    }

    override fun showAllLocations(locations: List<LocationItem>) {
        buttonsAdapter.differ.submitList(locations.toList())
    }
}