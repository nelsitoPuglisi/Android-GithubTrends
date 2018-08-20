package com.nelsito.githubtrends.usecase

import com.nelsito.githubtrends.model.GithubRepo
import com.nelsito.githubtrends.model.Readme
import io.reactivex.Observable

interface GithubRepoDetailRepository {
    fun detail(githubRepo: GithubRepo): Observable<GithubRepo>
    fun readme(githubRepo: GithubRepo): Observable<Readme>
}
