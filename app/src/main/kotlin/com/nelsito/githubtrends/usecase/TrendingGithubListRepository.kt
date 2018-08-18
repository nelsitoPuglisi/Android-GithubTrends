package com.nelsito.githubtrends.usecase

import com.nelsito.githubtrends.model.TrendingGithub
import io.reactivex.Observable


interface TrendingGithubListRepository {
    fun load(): Observable<TrendingGithub>
}
