package com.nelsito.githubtrends.usecase

class TrendingGithubListLoadViewState : TrendingGithubListViewState {

    override fun render(view: TrendingGithubListView) {
        view.showLoading()
    }
}
