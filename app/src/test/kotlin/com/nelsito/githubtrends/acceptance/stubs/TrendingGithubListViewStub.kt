package com.nelsito.githubtrends.acceptance.stubs

import com.nelsito.githubtrends.model.GithubRepo
import com.nelsito.githubtrends.usecase.TrendingGithubListView

class TrendingGithubListViewStub : TrendingGithubListView {

    private var message = ""

    override fun showItems(repos: List<GithubRepo>) {
        message = repos.map {
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
