package com.myapplication.utils

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.myapplication.model.information.ConsolidatedWeather
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

class LoadSvg {
    companion object {
        fun ImageView.loadSvg(url: HttpUrl) {
            val imageLoader = ImageLoader.Builder(this.context)
                .componentRegistry { add(SvgDecoder(this@loadSvg.context)) }
                .build()

            val request = ImageRequest.Builder(this.context)
                .crossfade(true)
                .crossfade(500)
                .data(url)
                .target(this)
                .build()

            imageLoader.enqueue(request)
        }

        fun getUrl(forecast: ConsolidatedWeather): HttpUrl {
            //get full url svg
            val typeWeather = forecast.weather_state_abbr
            return "${Constants.SVG_URL}${typeWeather}.svg".toHttpUrl()
        }
    }
}