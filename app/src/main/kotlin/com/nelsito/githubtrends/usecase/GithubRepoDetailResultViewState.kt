package com.nelsito.githubtrends.usecase

import com.nelsito.githubtrends.model.GithubRepo

class GithubRepoDetailResultViewState(val githubRepo: GithubRepo) : GithubRepoDetailViewState {
    override fun render(view: GithubRepoDetailView) {
        view.hideLoading()
        githubRepo.render(view)
    }

}
