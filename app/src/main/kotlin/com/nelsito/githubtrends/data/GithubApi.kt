package com.nelsito.githubtrends.data

import retrofit2.Retrofit

class GithubApi {
    private var service: GithubService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .build()

        service = retrofit.create(GithubService::class.java)
    }
}