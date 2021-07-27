package com.myapplication.network

import com.myapplication.model.information.ForecastResponse
import com.myapplication.model.search.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ForecastApi {
    //Location information, and a 5 day forecast
    @GET("location/{woeid}")
    suspend fun getLocationInformation(
        @Path("woeid")
        woeid: Int,
    ): Response<ForecastResponse>

    //Search for location
    @GET("location/search/?")
    suspend fun searchLocation(
        @Query("query")
        locationName: String,
    ): Response<LocationResponse>
}