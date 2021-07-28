package com.myapplication.model.search.data

import com.myapplication.model.search.LocationItem
import com.myapplication.model.search.db.LocationDataBase

class LocationsRepository(private val db: LocationDataBase) {

    suspend fun updateFavorite(location: LocationItem) =
        db.getLocationDao().updateFavorite(true, location.woeid)

    suspend fun removeAllFavorites() = db.getLocationDao().removeAllFavorites(false)

    fun getAll(): List<LocationItem> = db.getLocationDao().getAll()

    suspend fun getFavorite(): LocationItem = db.getLocationDao().getFavorite()

}