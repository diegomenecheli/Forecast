package com.myapplication.model.search.data

import android.content.Context
import com.myapplication.model.search.LocationItem
import com.myapplication.model.search.db.LocationDataBase
import com.myapplication.network.RetrofitInstance
import com.myapplication.presenter.forecast.ForecastPresenter
import com.myapplication.presenter.settings.SettingsHome
import kotlinx.coroutines.*

class LocationsDataSource (context: Context) {

    private var db: LocationDataBase = LocationDataBase.getInstance(context)
    private var locationsRepository: LocationsRepository = LocationsRepository(db)

//    fun searchLocation(term: String, callback: ForecastPresenter) {
//        GlobalScope.launch(Dispatchers.Main) {
//            val response = RetrofitInstance.api.searchLocation(term)
//            if (response.isSuccessful) {
//                response.body()?.let { locationResponse ->
//                    callback.onSuccess(locationResponse)
//                }
//                callback.onComplete()
//            } else {
//                callback.onError(response.message())
//                callback.onComplete()
//            }
//        }
//    }

    fun saveLocation(location: LocationItem){
        GlobalScope.launch(Dispatchers.Main) {
            locationsRepository.updateInsert(location)
        }
    }

    fun getAllLocations(callback: SettingsHome.Presenter){
        var allLocation: List<LocationItem>
        CoroutineScope(Dispatchers.IO).launch {
            allLocation = locationsRepository.getAll()

            withContext(Dispatchers.Main){
                callback.onSuccess(allLocation)
            }
        }
    }

    fun getFavoriteLocation(callback: SettingsHome.Presenter){
        var favoriteLocation: LocationItem
        CoroutineScope(Dispatchers.IO).launch {
            favoriteLocation = locationsRepository.getFavorite()

            withContext(Dispatchers.Main){
                callback.onFavorite(favoriteLocation)
            }
        }
    }
}