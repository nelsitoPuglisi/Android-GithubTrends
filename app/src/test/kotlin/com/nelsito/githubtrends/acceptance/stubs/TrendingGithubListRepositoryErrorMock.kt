package com.nelsito.githubtrends.acceptance.stubs

import android.accounts.NetworkErrorException
import com.nelsito.githubtrends.model.TrendingGithub
import com.nelsito.githubtrends.usecase.TrendingGithubListRepository
import io.reactivex.Observable

class TrendingGithubListRepositoryErrorMock : TrendingGithubListRepository {
    override fun load(): Observable<TrendingGithub> {
        return Observable.error(NetworkErrorException())
    }
}
