package com.nelsito.githubtrends.usecase

import com.nelsito.githubtrends.model.TrendingGithub
import io.reactivex.Observable

interface TrendingGithubListView {
    fun showLoading()
    fun showItems(trendingGithub: TrendingGithub)
    fun showError(it: Throwable)
}
