package com.myapplication.model.search.data

import com.myapplication.model.search.LocationItem
import com.myapplication.model.search.db.LocationDataBase

class LocationsRepository (private val db: LocationDataBase) {

    suspend fun updateInsert(location: LocationItem) = db.getLocationDao().updateInsert(location)

    fun getAll(): List<LocationItem> = db.getLocationDao().getAll()

    fun getFavorite(): LocationItem = db.getLocationDao().getFavorite()

}