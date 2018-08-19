package com.nelsito.githubtrends.data

import com.nelsito.githubtrends.data.dto.transform
import com.nelsito.githubtrends.model.GithubRepo
import com.nelsito.githubtrends.model.GithubUser
import com.nelsito.githubtrends.usecase.GithubRepoDetailRepository
import com.nelsito.githubtrends.usecase.TrendingGithubListRepository
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GithubApi : TrendingGithubListRepository, GithubRepoDetailRepository {
    override fun detail(githubRepo: GithubRepo): Observable<GithubRepo> {
        return service.getRepoDetail(githubRepo.owner.name, githubRepo.name)
                .map {
                    it.transform()
                }
    }

    override fun load(): Observable<List<GithubRepo>> {
        return service.getTrendingRepositories()
                .map {
                    it.items.map {
                        it.transform()
                    }
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