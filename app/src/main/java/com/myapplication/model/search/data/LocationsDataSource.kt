package com.myapplication.model.search.data

import android.content.Context
import com.myapplication.model.search.LocationItem
import com.myapplication.model.search.db.LocationDataBase
import com.myapplication.presenter.settings.SettingsHome
import kotlinx.coroutines.*

class LocationsDataSource(context: Context) {

    private var db: LocationDataBase = LocationDataBase.getInstance(context)
    private var locationsRepository: LocationsRepository = LocationsRepository(db)

    fun saveFavorite(location: LocationItem) {
        GlobalScope.launch(Dispatchers.Main) {
            locationsRepository.removeAllFavorites()
            locationsRepository.updateFavorite(location)
        }
    }

    fun getAllLocations(callback: SettingsHome.Presenter) {
        var allLocation: List<LocationItem>
        CoroutineScope(Dispatchers.IO).launch {
            allLocation = locationsRepository.getAll()

            withContext(Dispatchers.Main) {
                callback.onSuccess(allLocation)
            }
        }
    }
}