package com.nelsito.githubtrends.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.nelsito.githubtrends.R
import com.nelsito.githubtrends.model.GithubRepo
import kotlinx.android.synthetic.main.repo_cell.view.*

class TrendingGithubRepoAdapter(val clickListener: (githubRepo: GithubRepo) -> Unit) : RecyclerView.Adapter<TrendingGithubRepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.repo_cell, parent, false))

    private var items = listOf<GithubRepo>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], clickListener)

    fun setItems(repos: List<GithubRepo>) {
        items = repos
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(githubRepo: GithubRepo, clickListener: (githubRepo: GithubRepo) -> Unit) {
            itemView.userAvatar.loadFromUrl(githubRepo.owner.avatar)
            itemView.repoName.text = String.format("${githubRepo.owner.name}\\${githubRepo.name}")
            itemView.setOnClickListener {
                clickListener(githubRepo)
            }
        }
    }
}

fun ImageView.loadFromUrl(url: String) =
        Glide.with(this.context.applicationContext)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)!!
