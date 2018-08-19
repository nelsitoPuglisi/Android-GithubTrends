package com.nelsito.githubtrends.data

import com.nelsito.githubtrends.model.GithubRepo
import com.nelsito.githubtrends.model.GithubUser
import com.nelsito.githubtrends.model.TrendingGithub
import com.nelsito.githubtrends.usecase.TrendingGithubListRepository
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build()

        service = retrofit.create(GithubService::class.java)
    }
}