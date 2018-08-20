package com.nelsito.githubtrends.usecase

import com.nelsito.githubtrends.model.Readme

class GithubRepoDetailReadmeViewState(private val readme: Readme) : GithubRepoDetailViewState {
    override fun render(view: GithubRepoDetailView) {
        view.showReadMe(readme.html)
    }

}
