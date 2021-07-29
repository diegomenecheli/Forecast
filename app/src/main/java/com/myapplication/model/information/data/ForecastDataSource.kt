package com.myapplication.model.information.data

import android.content.Context
import android.util.Log
import com.myapplication.model.search.LocationItem
import com.myapplication.model.search.data.LocationsRepository
import com.myapplication.model.search.db.LocationDataBase
import com.myapplication.network.RetrofitInstance
import com.myapplication.presenter.forecast.ForecastHome
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForecastDataSource(context: Context) {
    private var db: LocationDataBase = LocationDataBase.getInstance(context)
    private var locationsRepository: LocationsRepository = LocationsRepository(db)

    fun getFavoriteLocation(callback: ForecastHome.Presenter) {
        var favoriteLocation: LocationItem
        CoroutineScope(Dispatchers.IO).launch {
            favoriteLocation = locationsRepository.getFavorite()
            withContext(Dispatchers.Main) {
                val response = if (favoriteLocation != null) {
                    RetrofitInstance.api.getLocationInformation(favoriteLocation.woeid)
                } else {
                    RetrofitInstance.api.getLocationInformation(742676)
                }
                if (response.isSuccessful) {
                    response.body()?.let { forecastResponse ->
                        callback.onSuccess(forecastResponse)
                    }
                    callback.onComplete()
                } else {
                    callback.onError(response.message())
                    callback.onComplete()
                }
            }
        }
    }
}