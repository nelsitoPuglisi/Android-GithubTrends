package com.nelsito.githubtrends.data

import com.nelsito.githubtrends.model.GithubRepo
import com.nelsito.githubtrends.model.GithubUser
import com.nelsito.githubtrends.model.TrendingGithub
import com.nelsito.githubtrends.usecase.TrendingGithubListRepository
import io.reactivex.Observable
import retrofit2.Retrofit

class GithubApi : TrendingGithubListRepository {
    override fun load(): Observable<TrendingGithub> {
        return service.getTrendingRepositories()
                .map {
                    TrendingGithub(
                        it.items.map {
                            GithubRepo(GithubUser(it.owner.login, it.owner.id.toString(), it.owner.avatarUrl), it.name, it.id.toString())
                        }
                    )
                }
    }

    private var service: GithubService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .build()

        service = retrofit.create(GithubService::class.java)
    }
}