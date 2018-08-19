package com.nelsito.githubtrends.usecase

class GithubRepoDetailLoadViewState : GithubRepoDetailViewState{
    override fun render(view: GithubRepoDetailView) {
        view.showLoading()
    }

}
