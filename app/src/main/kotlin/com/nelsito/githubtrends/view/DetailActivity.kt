package com.nelsito.githubtrends.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nelsito.githubtrends.R
import com.nelsito.githubtrends.data.GithubApi
import com.nelsito.githubtrends.model.GithubRepo
import com.nelsito.githubtrends.usecase.GithubRepoDetail
import com.nelsito.githubtrends.usecase.GithubRepoDetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailActivity : AppCompatActivity(), GithubRepoDetailView {

    companion object {
        private const val INTENT_EXTRA_PARAM_REPO = "com.nelsito.INTENT_EXTRA_PARAM_REPO"

        fun callingIntent(context: Context, githubRepo: GithubRepo): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_REPO, githubRepo)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val repo = intent.getParcelableExtra(INTENT_EXTRA_PARAM_REPO) as GithubRepo

        GithubRepoDetail(this, GithubApi()).load(Schedulers.io(), AndroidSchedulers.mainThread())

    }
}