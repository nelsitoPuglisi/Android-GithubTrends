package com.nelsito.githubtrends.acceptance.stubs

import com.nelsito.githubtrends.usecase.TrendingGithubListRepository
import com.nelsito.githubtrends.usecase.TrendingGithubListView
import io.reactivex.Observable
import usecase.TrendingGithubList

class TrendingGithubListViewStub(repository: TrendingGithubListRepository) : TrendingGithubListView {
    init {
        TrendingGithubList(this, repository)
    }
    override fun loadFirstPage(): Observable<Boolean> {
        return Observable.just(true)
    }

    private var loading = ""

    override fun showLoading() {
        loading = "Loading!"
    }

    fun assert(): String {
        return "Trending Github List Screen\n" +
                loading
    }
}
