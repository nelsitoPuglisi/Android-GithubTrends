package com.nelsito.githubtrends.data.dto

import com.google.gson.annotations.SerializedName

data class CommitCommitter(
        @SerializedName("name") val name: String,
        @SerializedName("email") val email: String,
        @SerializedName("date") val date: String
)