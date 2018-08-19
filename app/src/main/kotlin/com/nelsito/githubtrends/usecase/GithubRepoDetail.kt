package com.nelsito.githubtrends.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler

class GithubRepoDetail(val view: GithubRepoDetailView, val repository: GithubRepoDetailRepository) {
    fun load(subscribeSchedulers: Scheduler, observeScheduler: Scheduler) {
        Observable.just(GithubRepoDetailLoadViewState().render(view))
                .concatMap {
                    repository.detail()
                }
                .map {
                    GithubRepoDetailResultViewState(it).render(view)
                }
                .onErrorReturn {
                    GithubRepoDetailViewState(it).render(view)
                }
                .subscribeOn(subscribeSchedulers)
                .observeOn(observeScheduler)
                .subscribe()
    }

}
