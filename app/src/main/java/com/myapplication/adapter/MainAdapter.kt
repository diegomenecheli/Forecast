package com.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.R
import com.myapplication.model.information.ConsolidatedWeather
import com.myapplication.utils.Constants.Companion.SVG_URL
import com.myapplication.utils.GetDayWeek.Companion.getWeekDayName
import com.myapplication.utils.LoadSvg.Companion.getUrl
import com.myapplication.utils.LoadSvg.Companion.loadSvg
import kotlinx.android.synthetic.main.item_forecast.view.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.lang.Math.round


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
        holder.itemView.apply {
            tv_day_forecast.text = getWeekDayName(forecast.applicable_date)
            tv_min_and_max.text = "${round(forecast.min_temp)}°/${round(forecast.max_temp)}°"
            iv_weather.loadSvg(getUrl(forecast))
            setOnClickListener {
                Log.d("xuxa", "getWeekDayName:")
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

}