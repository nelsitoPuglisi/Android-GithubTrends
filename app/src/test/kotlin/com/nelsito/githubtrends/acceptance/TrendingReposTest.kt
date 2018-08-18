package com.nelsito.githubtrends.acceptance

import com.nelsito.githubtrends.acceptance.stubs.TrendingGithubListRepositoryErrorMock
import com.nelsito.githubtrends.acceptance.stubs.TrendingGithubListRepositoryMock
import com.nelsito.githubtrends.acceptance.stubs.TrendingGithubListViewStub
import com.nelsito.githubtrends.usecase.TrendingGithubListView
import usecase.TrendingGithubList
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals

class TrendingReposTest : Spek({
    Feature("Trending Github List"){
        Scenario("User opens the list") {
            var view = TrendingGithubListViewStub(TrendingGithubListRepositoryMock())
            When("a user opens the list"){
                view.loadFirstPage()
            }

            Then("the view shows a loading message") {
                val expected =
                        "Trending Github List Screen\n" +
                        "Loading!"

                assertEquals(expected, view.assert())
            }

            When("there is an error") {
                view = TrendingGithubListViewStub(TrendingGithubListRepositoryErrorMock())
                view.loadFirstPage()
            }

            Then("the hides the loading and shows an error message") {
                val expected =
                        "Trending Github List Screen\n" +
                                "There was an error"

                assertEquals(expected, view.assert())
            }
        }
    }
})