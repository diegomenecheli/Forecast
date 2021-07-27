package com.myapplication.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.R
import com.myapplication.model.search.LocationItem
import com.myapplication.ui.main.MainActivity
import kotlinx.android.synthetic.main.item_radio_buttons.view.*


class ButtonsAdapter : RecyclerView.Adapter<ButtonsAdapter.ButtonsViewHolder>() {

    inner class ButtonsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<LocationItem>() {
        override fun areItemsTheSame(
            oldItem: LocationItem,
            newItem: LocationItem
        ): Boolean {
            return oldItem.woeid == newItem.woeid
        }

        override fun areContentsTheSame(
            oldItem: LocationItem,
            newItem: LocationItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onBindViewHolder(holder: ButtonsViewHolder, position: Int) {
        val location = differ.currentList[position]
        Log.d("xuxa", "onBindViewHolder: " + location)
        holder.itemView.apply {
            radio_button.text = location.title
            setOnClickListener{
                //TODO FAVORITE PLACE
                (context as Activity).finish()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonsViewHolder =
        ButtonsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_radio_buttons, parent, false)
        )

    override fun getItemCount(): Int = differ.currentList.size


}