package com.nelsito.githubtrends.acceptance.stubs

import com.nelsito.githubtrends.model.TrendingGithub
import com.nelsito.githubtrends.usecase.*
import io.reactivex.Observable
import io.reactivex.Scheduler

class TrendingGithubListViewStub : TrendingGithubListView {

    override fun loadFirstPage(): Observable<Boolean> {
        return Observable.just(true)
    }

    private var message = ""

    override fun showItems(trendingGithub: TrendingGithub) {
        message = "size: " + trendingGithub.size() + "\n" +
                trendingGithub.repos.map {
                    it.owner.name + "\\" + it.name + "\n"
                }
                .reduce {
                    it, acc -> it + acc
                }

    }

    override fun showLoading() {
        message = "Loading!"
    }

    override fun showError(it: Throwable) {
        message = "There was an error"
    }

    fun assert(): String {
        return "Trending Github List Screen\n" +
                message
    }
}
