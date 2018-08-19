package com.nelsito.githubtrends.data.dto

import com.google.gson.annotations.SerializedName

data class TrendingGithubResponse(
        @SerializedName("total_count") val totalCount: Int,
        @SerializedName("incomplete_results") val incompleteResults: Boolean,
        @SerializedName("items") val items: List<Item>
)