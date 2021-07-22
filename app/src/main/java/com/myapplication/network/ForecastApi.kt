package com.myapplication.network

import com.myapplication.model.information.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ForecastApi {
    //Location information, and a 5 day forecast
    @GET("location/{woeid}")
    suspend fun getLocationInformation(
        @Path("woeid")
        woeid: Int,
    ): Response<ForecastResponse>
}