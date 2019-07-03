package com.perry.wanandroid.kt.model.repository

import com.perry.wanandroid.kt.base.BaseRepository
import com.perry.wanandroid.kt.model.bean.ArticleList
import com.perry.wanandroid.kt.model.bean.Response
import com.perry.wanandroid.kt.model.romote.WanRetrofitClient

class HomeRepository : BaseRepository() {

    suspend fun getHomeArticles(page: Int): Response<ArticleList> {
        return apiCall { WanRetrofitClient.service.getHomeArticles(page) }
    }

}