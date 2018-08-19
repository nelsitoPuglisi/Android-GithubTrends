package com.nelsito.githubtrends.acceptance

import com.nelsito.githubtrends.acceptance.stubs.TrendingGithubListViewStub
import com.nelsito.githubtrends.model.GithubRepo
import com.nelsito.githubtrends.model.GithubUser
import com.nelsito.githubtrends.model.TrendingGithub
import com.nelsito.githubtrends.usecase.TrendingGithubList
import com.nelsito.githubtrends.usecase.TrendingGithubListRepository
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock

class TrendingGithubTest {

    private lateinit var testScheduler: TestScheduler

    @Before
    fun setup() {
        testScheduler = TestScheduler()
    }

    @Test
    fun aUserOpenList_showsLoading() {
        // Given
        val repository = mock(TrendingGithubListRepository::class.java)
        given(repository.load()).willReturn(Observable.empty())
        var view = TrendingGithubListViewStub()

        // When
        TrendingGithubList(view, repository).load(testScheduler, testScheduler)
        // Then
        val expected =
                "Trending Github List Screen\n" +
                        "Loading!"

        assertEquals(expected, view.assert())
    }

    @Test
    fun aUserOpenList_showsItems_whenFinished() {

        // Given
        val repository = mock(TrendingGithubListRepository::class.java)
        val repos = listOf(
                GithubRepo(GithubUser("userA", "userId1", ""), "repoA", "repoId1"),
                GithubRepo(GithubUser("userB", "userId2", ""), "repoB", "repoId2"))
        given(repository.load()).willReturn(Observable.just(TrendingGithub(repos)))
        var view = TrendingGithubListViewStub()

        // When
        TrendingGithubList(view, repository).load(testScheduler, testScheduler)
        testScheduler.triggerActions()

        // Then
        val expected =
                "Trending Github List Screen\n" +
                        "size: 2\n" +
                        "userA\\repoA\n" +
                        "userB\\repoB\n"

        assertEquals(expected, view.assert())
    }

    @Test
    fun aUserOpenList_showsError() {
        // Given
        val repository = mock(TrendingGithubListRepository::class.java)
        var view = TrendingGithubListViewStub()

        // When
        TrendingGithubList(view, repository).load(testScheduler, testScheduler)
        testScheduler.triggerActions()

        // Then
        val expected =
                "Trending Github List Screen\n" +
                        "There was an error"

        assertEquals(expected, view.assert())
    }
}