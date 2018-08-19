package com.nelsito.githubtrends.data

import com.nelsito.githubtrends.data.dto.TrendingGithubResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("search/repositories?q=created:>2018-08-01+language:kotlin&sort=stars&order=desc")
    fun getTrendingRepositories(): Observable<TrendingGithubResponse>

    @GET("repos/{user}/{repo}")
    fun getRepoDetail(@Path("user") user: String, @Path("repo") repo: String): Observable<TrendingGithubResponse>
}