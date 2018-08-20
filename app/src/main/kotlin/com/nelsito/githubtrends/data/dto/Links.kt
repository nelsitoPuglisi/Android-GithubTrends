package com.nelsito.githubtrends.data.dto

import com.google.gson.annotations.SerializedName

data class Links(
        @SerializedName("self") val self: String,
        @SerializedName("git") val git: String,
        @SerializedName("html") val html: String
)