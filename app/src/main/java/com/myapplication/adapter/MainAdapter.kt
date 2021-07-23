package com.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.myapplication.R
import com.myapplication.model.information.ConsolidatedWeather
import com.myapplication.utils.Constants.Companion.SVG_URL
import kotlinx.android.synthetic.main.item_forecast.view.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.lang.Math.round
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class MainAdapter : RecyclerView.Adapter<MainAdapter.ForecastViewHolder>() {

    inner class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<ConsolidatedWeather>() {
        override fun areItemsTheSame(
            oldItem: ConsolidatedWeather,
            newItem: ConsolidatedWeather
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ConsolidatedWeather,
            newItem: ConsolidatedWeather
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = differ.currentList[position]
        val typeWeather = forecast.weather_state_abbr
        val svgUrl = "$SVG_URL/${typeWeather}.svg".toHttpUrl()
        holder.itemView.apply {
            tv_day_forecast.text = getWeekDayName(forecast.applicable_date)
            tv_min_and_max.text = "${round(forecast.min_temp)}°/${round(forecast.max_temp)}°"
            iv_weather.load(svgUrl)

            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(forecast)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
        ForecastViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_forecast, parent, false)
        )

    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((ConsolidatedWeather) -> Unit)? = null

    fun setOnClickListener(listener: (ConsolidatedWeather) -> Unit) {
        onItemClickListener = listener
    }

    fun getWeekDayName(s: String?): String? {
        val dtfOutput: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH)
        return LocalDate.parse(s).format(dtfOutput)
    }

    fun getTodayDayName(s: String?): String? {
        val dtfOutput: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH)
        return LocalDate.parse(s).format(dtfOutput)
    }

}