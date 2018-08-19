package com.nelsito.githubtrends.usecase

import com.nelsito.githubtrends.model.GithubRepo
import io.reactivex.Observable

interface GithubRepoDetailRepository {
    fun detail(githubRepo: GithubRepo): Observable<GithubRepo>
}
