package com.nelsito.githubtrends.model

import android.os.Parcelable
import com.nelsito.githubtrends.usecase.GithubRepoDetailView
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubRepo(val owner: GithubUser, val name: String, val id: String, val description: String, val stars: Int = 0) : Parcelable {

    private var commits = emptyList<Commit>()

    fun setCommits(commits: List<Commit>) : GithubRepo {
        this.commits = commits
        return this
    }

    fun render(view: GithubRepoDetailView) {
        view.loadAvatar(owner.avatar)
        view.setName(owner.name + "\\" + name)
        view.description(description)
        view.showCommitsCount(commits.size)
    }
}