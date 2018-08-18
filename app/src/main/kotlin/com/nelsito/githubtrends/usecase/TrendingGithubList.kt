package usecase

import com.nelsito.githubtrends.usecase.*

class TrendingGithubList(val view: TrendingGithubListView, val repository: TrendingGithubListRepository) {
    init {
        view.loadFirstPage()
                .flatMap { _ ->  repository.load() }
                .map {
                    trendingRepos ->
                    TrendingGithubListResultViewState(trendingRepos) as TrendingGithubListViewState
                }
                .startWith {
                    TrendingGithubListLoadingViewState()
                }
                .onErrorReturn {
                    TrendingGithubListErrorViewState(it)
                }.subscribe{
                    it.render(view)
                }
    }
}
