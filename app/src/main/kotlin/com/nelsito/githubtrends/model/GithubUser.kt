package com.nelsito.githubtrends.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubUser(val name: String, val id: String, val avatar: String): Parcelable
