package com.myapplication.model.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "location")
data class LocationItem(
    @PrimaryKey(autoGenerate = false)
    val woeid: Int,
    val title: String,
    val favorite: Boolean, //new Boolean column added
) : Serializable