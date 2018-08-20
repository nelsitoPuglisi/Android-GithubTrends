package com.nelsito.githubtrends.data.dto

import com.google.gson.annotations.SerializedName
import com.nelsito.githubtrends.model.Readme

data class ReadMeResponse(
        @SerializedName("name") val name: String,
        @SerializedName("path") val path: String,
        @SerializedName("sha") val sha: String,
        @SerializedName("size") val size: Int,
        @SerializedName("url") val url: String,
        @SerializedName("html_url") val htmlUrl: String,
        @SerializedName("git_url") val gitUrl: String,
        @SerializedName("download_url") val downloadUrl: String,
        @SerializedName("type") val type: String,
        @SerializedName("content") val content: String,
        @SerializedName("encoding") val encoding: String,
        @SerializedName("_links") val links: Links
)