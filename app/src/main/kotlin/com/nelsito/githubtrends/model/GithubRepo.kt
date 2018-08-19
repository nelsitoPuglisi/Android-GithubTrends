package com.nelsito.githubtrends.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubRepo(val owner: GithubUser, val name: String, val id: String) : Parcelable