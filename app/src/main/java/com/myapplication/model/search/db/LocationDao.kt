package com.myapplication.model.search.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myapplication.model.search.LocationItem

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInsert(location: LocationItem): Long

    @Query("SELECT * FROM location")
    fun getAll(): List<LocationItem>

    @Query("SELECT * FROM location WHERE favorite = :favorite")
    suspend fun getFavorite(favorite: Boolean = true): LocationItem

    @Query("UPDATE location SET favorite = :favorite WHERE woeid =:id")
    suspend fun updateFavorite(favorite: Boolean, id: Int)

    @Query("UPDATE location SET favorite = :favorite")
    suspend fun removeAllFavorites(favorite: Boolean)
}