package com.nelsito.githubtrends.usecase

class TrendingGithubListLoadingViewState : TrendingGithubListViewState {
    override fun render(view: TrendingGithubListView) {
        view.showLoading()
    }
}
