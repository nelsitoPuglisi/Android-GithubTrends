package com.nelsito.githubtrends.data.dto

import com.google.gson.annotations.SerializedName

data class Verification(
        @SerializedName("verified") val verified: Boolean,
        @SerializedName("reason") val reason: String,
        @SerializedName("signature") val signature: String,
        @SerializedName("payload") val payload: String
)