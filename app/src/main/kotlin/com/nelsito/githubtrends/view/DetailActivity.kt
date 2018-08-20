package com.nelsito.githubtrends.view

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.text.style.StyleSpan
import android.view.View
import com.nelsito.githubtrends.R
import com.nelsito.githubtrends.data.GithubApi
import com.nelsito.githubtrends.model.GithubRepo
import com.nelsito.githubtrends.usecase.GithubRepoDetail
import com.nelsito.githubtrends.usecase.GithubRepoDetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), GithubRepoDetailView {
    override fun showReadMe(html: String) {
        runOnUiThread {
            repoReadme.text = Html.fromHtml(html)
        }
    }

    override fun showCommitsCount(commits: Int) {
        runOnUiThread {
            commitsCount.text = Html.fromHtml(resources.getQuantityString(R.plurals.commits_count, commits, commits))
            commitsCount.visibility = View.VISIBLE
        }
    }

    override fun description(description: String) {
        runOnUiThread {
            repoDesc.text = description
        }
    }

    override fun loadAvatar(avatar: String) {
        runOnUiThread {
            userAvatar.loadFromUrl(avatar)
        }
    }

    override fun setName(name: String) {
        runOnUiThread {
            repoName.text = name
        }
    }

    override fun ownerName(name: String) {

    }

    override fun hideLoading() {
        runOnUiThread {
            progressBar.visibility = View.GONE
        }
    }

    override fun showError(it: Throwable) {
        runOnUiThread {
            errorMessage.text = it.message
            errorMessage.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    override fun showLoading() {
        runOnUiThread {
            progressBar.visibility = View.VISIBLE
        }
    }

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

        GithubRepoDetail(this, GithubApi()).load(repo, Schedulers.io(), AndroidSchedulers.mainThread())
    }
}
