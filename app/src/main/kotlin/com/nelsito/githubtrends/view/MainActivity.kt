package com.nelsito.githubtrends.view

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.nelsito.githubtrends.R
import com.nelsito.githubtrends.data.GithubApi
import com.nelsito.githubtrends.model.GithubRepo
import com.nelsito.githubtrends.usecase.TrendingGithubList
import com.nelsito.githubtrends.usecase.TrendingGithubListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TrendingGithubListView {

    private lateinit var adapter: TrendingGithubRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = TrendingGithubRepoAdapter {

            /*
            Note: In case that a detail activity wasn't needed anymore, and is replace with, for example, a bottom sheet,
            only change here and make the bottom sheet to implement `GithubRepoDetailView` and call `GithubRepoDetail.load()`
             */
            val intent  = DetailActivity.callingIntent(this@MainActivity, it)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        TrendingGithubList(this, GithubApi()).load(Schedulers.io(), AndroidSchedulers.mainThread())
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun showItems(repos: List<GithubRepo>) {
        runOnUiThread {
            progressBar.visibility = View.GONE
            adapter.setItems(repos)
            recyclerView.visibility = View.VISIBLE
        }
    }

    override fun showError(it: Throwable) {
        errorMessage.text = it.message
        errorMessage.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }
}
