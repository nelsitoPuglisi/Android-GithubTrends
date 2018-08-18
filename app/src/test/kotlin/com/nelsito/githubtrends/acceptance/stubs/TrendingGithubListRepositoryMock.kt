package com.nelsito.githubtrends.acceptance.stubs

import com.nelsito.githubtrends.model.TrendingGithub
import com.nelsito.githubtrends.usecase.TrendingGithubListRepository
import io.reactivex.Observable

class TrendingGithubListRepositoryMock : TrendingGithubListRepository {
    override fun load(): Observable<TrendingGithub> {
        return Observable.just(TrendingGithub())
    }

}
