package com.nelsito.githubtrends.usecase

import io.reactivex.Observable

interface TrendingGithubListView {
    fun showLoading()
    fun loadFirstPage() : Observable<Boolean>
}
