package com.nelsito.githubtrends.acceptance

import android.accounts.NetworkErrorException
import com.nelsito.githubtrends.acceptance.stubs.TrendingGithubListViewStub
import com.nelsito.githubtrends.model.GithubRepo
import com.nelsito.githubtrends.model.GithubUser
import com.nelsito.githubtrends.model.TrendingGithub
import com.nelsito.githubtrends.usecase.TrendingGithubListRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class TrendingGithubTest {

    val immediate = object : Scheduler() {
        override fun scheduleDirect(run: Runnable,
                                    delay: Long, unit: TimeUnit): Disposable {
            return super.scheduleDirect(run, 0, unit)
        }

        override fun createWorker(): Scheduler.Worker {
            return ExecutorScheduler.ExecutorWorker(
                    Executor { it.run() })
        }
    }

    @Before
    fun setup() {
        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }

    @After
    fun finally() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun aUserOpenList_showsLoading() {
        // Given
        val repository = mock(TrendingGithubListRepository::class.java)
        `when`(repository.load()).thenReturn(Observable.empty())

        // When
        var view = TrendingGithubListViewStub(repository, immediate)

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
        `when`(repository.load()).thenReturn(Observable.just(TrendingGithub(repos)))

        // When
        var view = TrendingGithubListViewStub(repository, immediate)

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

        // When
        `when`(repository.load()).thenReturn(Observable.error(NetworkErrorException()))

        var view = TrendingGithubListViewStub(repository, immediate)

        // Then
        val expected =
                "Trending Github List Screen\n" +
                        "There was an error"

        assertEquals(expected, view.assert())

    }
}