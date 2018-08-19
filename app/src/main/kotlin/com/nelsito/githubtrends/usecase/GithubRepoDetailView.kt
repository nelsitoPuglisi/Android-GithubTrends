package com.nelsito.githubtrends.usecase

interface GithubRepoDetailView {
    fun showLoading()
    fun loadAvatar(avatar: String)
    fun setName(name: String)
    fun ownerName(name: String)
    fun hideLoading()
    fun showError(it: Throwable)
    fun description(description: String)
}
