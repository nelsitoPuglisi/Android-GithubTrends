package com.nelsito.githubtrends.usecase

class TrendingGithubListErrorViewState(val it: Throwable) : TrendingGithubListViewState {
    override fun render(view: TrendingGithubListView) {
        view.showError(it)
    }
}

