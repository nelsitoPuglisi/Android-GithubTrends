package com.nelsito.githubtrends.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
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
            val intent  = DetailActivity.callingIntent(this@MainActivity, it)
            startActivity(intent)
        }
        recyclerView.layoutManager = GridLayoutManager(this, 2)
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
