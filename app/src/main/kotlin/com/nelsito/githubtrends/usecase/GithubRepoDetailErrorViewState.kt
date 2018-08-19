package com.nelsito.githubtrends.usecase

class GithubRepoDetailErrorViewState(val it: Throwable) : GithubRepoDetailViewState {
    override fun render(view: GithubRepoDetailView) {
        view.hideLoading()
        view.showError(it)
    }
}
