package com.nelsito.githubtrends.data.dto

import com.google.gson.annotations.SerializedName

data class Parent(
        @SerializedName("sha") val sha: String,
        @SerializedName("url") val url: String,
        @SerializedName("html_url") val htmlUrl: String
)