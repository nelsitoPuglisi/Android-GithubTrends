package com.nelsito.githubtrends.model

class TrendingGithub(val repos: List<GithubRepo>) {
    fun size(): Int {
        return repos.size
    }
}
