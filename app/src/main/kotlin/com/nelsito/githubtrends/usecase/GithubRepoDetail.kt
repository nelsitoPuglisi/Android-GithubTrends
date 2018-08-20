package com.nelsito.githubtrends.usecase

import com.nelsito.githubtrends.model.GithubRepo
import io.reactivex.Observable
import io.reactivex.Scheduler

class GithubRepoDetail(val view: GithubRepoDetailView, val repository: GithubRepoDetailRepository) {
    fun load(githubRepo: GithubRepo, subscribeSchedulers: Scheduler, observeScheduler: Scheduler) {
        Observable.just(GithubRepoDetailLoadViewState().render(view))
                .concatMap {
                    repository.detail(githubRepo)
                    .map {
                        GithubRepoDetailResultViewState(it).render(view)
                    }
                    .onErrorReturn {
                        GithubRepoDetailErrorViewState(it).render(view)
                    }
                }
                .concatMap {
                    repository.readme(githubRepo)
                    .map {
                        GithubRepoDetailReadmeViewState(it).render(view)
                    }
                    .onErrorReturn {
                        GithubRepoDetailErrorViewState(it).render(view)
                    }
                }
                .subscribeOn(subscribeSchedulers)
                .observeOn(observeScheduler)
                .subscribe()
    }

}
