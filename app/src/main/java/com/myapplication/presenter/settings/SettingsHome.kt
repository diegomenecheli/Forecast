package com.myapplication.presenter.settings

import com.myapplication.model.search.LocationItem

interface SettingsHome {
    interface Presenter{
        fun onSuccess(location: List<LocationItem>)
        fun onFavorite(location: LocationItem){}
        fun onError(message: String)
        fun onComplete()
    }
}