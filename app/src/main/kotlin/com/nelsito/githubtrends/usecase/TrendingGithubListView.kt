package com.nelsito.githubtrends.usecase

import com.nelsito.githubtrends.model.GithubRepo

interface TrendingGithubListView {
    fun showLoading()
    fun showItems(repos: List<GithubRepo>)
    fun showError(it: Throwable)
}
