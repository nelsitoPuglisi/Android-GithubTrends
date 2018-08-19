package com.nelsito.githubtrends.model

import android.os.Parcelable
import com.nelsito.githubtrends.usecase.GithubRepoDetailView
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubRepo(val owner: GithubUser, val name: String, val id: String, val description: String) : Parcelable {
    fun render(view: GithubRepoDetailView) {
        view.loadAvatar(owner.avatar)
        view.setName(name)
        view.ownerName(owner.name)
        view.description(description)
    }
}