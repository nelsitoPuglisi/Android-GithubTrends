package com.nelsito.githubtrends.usecase

import com.nelsito.githubtrends.model.TrendingGithub

class TrendingGithubListResultViewState(val trendingRepos: TrendingGithub) : TrendingGithubListViewState {
    override fun render(view: TrendingGithubListView) {
        view.showItems(trendingRepos)
    }
}
