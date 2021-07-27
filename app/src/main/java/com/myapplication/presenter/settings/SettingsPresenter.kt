package com.myapplication.presenter.settings

import com.myapplication.model.search.LocationItem
import com.myapplication.model.search.data.LocationsDataSource
import com.myapplication.presenter.ViewHome

class SettingsPresenter(
    val view: ViewHome.Settings,
    private val dataSource: LocationsDataSource
) : SettingsHome.Presenter {


    fun getAll() {
        this.view.showProgressBar()
        this.dataSource.getAllLocations(this)
    }

    fun saveFavorite(location: LocationItem) {
        this.view.showProgressBar()
        this.dataSource.saveFavorite(location)
    }


    override fun onSuccess(location: List<LocationItem>) {
        this.view.showAllLocations(location)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }
}