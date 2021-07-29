package com.myapplication.model.information

import java.io.Serializable

data class Source(
    val crawl_rate: Int,
    val slug: String,
    val title: String,
    val url: String
) : Serializable