package com.nelsito.githubtrends.usecase

import com.nelsito.githubtrends.usecase.TrendingGithubListView

interface TrendingGithubListViewState {

    fun render(view: TrendingGithubListView)
}
