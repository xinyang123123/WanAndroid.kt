package com.perry.wanandroid.kt.model.repository

import com.perry.wanandroid.kt.base.BaseRepository
import com.perry.wanandroid.kt.model.bean.ArticleList
import com.perry.wanandroid.kt.model.bean.Response
import com.perry.wanandroid.kt.model.bean.User
import com.perry.wanandroid.kt.model.romote.WanRetrofitClient

class UserRepository : BaseRepository() {

    suspend fun login(account: String, password: String): Response<User> {
        return apiCall { WanRetrofitClient.service.login(account, password) }
    }

    suspend fun logout(): Response<Any> {
        return apiCall { WanRetrofitClient.service.logout() }
    }

    suspend fun collectArticle(id: Int): Response<ArticleList> {
        return apiCall { WanRetrofitClient.service.collectArticle(id) }
    }

    suspend fun cancelCollectArticle(id: Int): Response<ArticleList> {
        return apiCall { WanRetrofitClient.service.cancelCollectArticle(id) }
    }

    suspend fun getCollectArticles(page: Int): Response<ArticleList> {
        return apiCall { WanRetrofitClient.service.getCollectArticles(page) }
    }
}