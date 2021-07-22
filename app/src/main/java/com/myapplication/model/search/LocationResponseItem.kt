package com.myapplication.model.search

data class LocationResponseItem(
    val latt_long: String,
    val location_type: String,
    val title: String,
    val woeid: Int
)