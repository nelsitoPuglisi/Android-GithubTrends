package com.nelsito.githubtrends.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler

class TrendingGithubList(val view: TrendingGithubListView,
                         val repository: TrendingGithubListRepository) {

    fun load(subscribeSchedulers: Scheduler, observeScheduler: Scheduler) {
        Observable.just(TrendingGithubListLoadViewState().render(view))
            .concatMap {
                repository.load()
            }
            .map {
                TrendingGithubListResultViewState(it).render(view)
            }
            .onErrorReturn {
                TrendingGithubListErrorViewState(it).render(view)
            }
            .subscribeOn(subscribeSchedulers)
            .observeOn(observeScheduler)
            .subscribe()
    }

    /*init {
        view.loadFirstPage()
                .doOnNext {
                    _ -> Log.d("", "")
                }
                .flatMap {
                    repository.load()
                }
                .map {
                    TrendingGithubListResultViewState(it) as TrendingGithubListViewState
                }
                .startWith {
                    TrendingGithubListLoadViewState()
                }
                .onErrorReturn {
                    TrendingGithubListErrorViewState(it)
                }
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe{
                    it.render(view)
                }
    }*/
}
