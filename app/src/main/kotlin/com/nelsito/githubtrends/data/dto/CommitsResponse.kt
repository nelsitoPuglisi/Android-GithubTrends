package com.nelsito.githubtrends.data.dto

import com.google.gson.annotations.SerializedName
import com.nelsito.githubtrends.model.Commit

data class CommitsResponse(
        @SerializedName("sha") val sha: String,
        @SerializedName("node_id") val nodeId: String,
        @SerializedName("commit") val commit: CommitResponse,
        @SerializedName("url") val url: String,
        @SerializedName("html_url") val htmlUrl: String,
        @SerializedName("comments_url") val commentsUrl: String,
        @SerializedName("commitAuthor") val author: Author,
        @SerializedName("committer") val committer: Committer,
        @SerializedName("parents") val parents: List<Parent>
)

fun CommitsResponse.transform() : Commit {
    return Commit()
}