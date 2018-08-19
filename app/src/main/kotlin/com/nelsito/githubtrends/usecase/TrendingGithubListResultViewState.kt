package com.nelsito.githubtrends.usecase

import com.nelsito.githubtrends.model.GithubRepo

class TrendingGithubListResultViewState(val trendingRepos: List<GithubRepo>) : TrendingGithubListViewState {
    override fun render(view: TrendingGithubListView) {
        view.showItems(trendingRepos)
    }
}
