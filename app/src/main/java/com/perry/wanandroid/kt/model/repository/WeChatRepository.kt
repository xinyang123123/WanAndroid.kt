package com.perry.wanandroid.kt.model.repository

import com.perry.wanandroid.kt.base.BaseRepository
import com.perry.wanandroid.kt.model.bean.ArticleList
import com.perry.wanandroid.kt.model.bean.Response
import com.perry.wanandroid.kt.model.bean.SystemParent
import com.perry.wanandroid.kt.model.romote.WanRetrofitClient

class WeChatRepository : BaseRepository() {

    suspend fun getBlogType(): Response<List<SystemParent>> {
        return apiCall { WanRetrofitClient.service.getBlogType() }
    }

    suspend fun getBlogArticle(id: Int, page: Int): Response<ArticleList> {
        return apiCall { WanRetrofitClient.service.getBlogArticle(id, page) }
    }
}