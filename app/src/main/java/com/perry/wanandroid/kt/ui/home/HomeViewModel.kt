package com.perry.wanandroid.kt.ui.home

import androidx.lifecycle.MutableLiveData
import com.perry.wanandroid.kt.PAGE_START
import com.perry.wanandroid.kt.base.BaseViewModel
import com.perry.wanandroid.kt.model.bean.Article
import com.perry.wanandroid.kt.model.bean.RequestStatus
import com.perry.wanandroid.kt.model.repository.HomeRepository
import com.perry.wanandroid.kt.model.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel : BaseViewModel() {

    private val homeRepository by lazy { HomeRepository() }
    private val userRepository by lazy { UserRepository() }

    var listData: MutableLiveData<List<Article>> = MutableLiveData()
    var loadMoreData: MutableLiveData<List<Article>> = MutableLiveData()
    var page = PAGE_START

    fun getHomeArticles() {
        launch {
            page = PAGE_START
            srlStatus.value = RequestStatus.START
            val result = withContext(Dispatchers.IO) { homeRepository.getHomeArticles(page) }
            resolveResponse(result, {
                listData.value = result.data.datas
                page++
            })
        }
    }

    fun loadMoreHomeArticles() {
        launch {
            val result = withContext(Dispatchers.IO) { homeRepository.getHomeArticles(page) }
            resolveResponse(result, {
                if (result.data.over) loadMoreStatus.value = RequestStatus.COMPLETE
                loadMoreData.value = result.data.datas
                page++
            }, isLoadMore = true)
        }
    }

    fun collectArticle(id: Int) {
        launch {
            var response = userRepository.collectArticle(id)
            resolveResponse(response, {})
        }
    }

    fun cancelCollectArticle(id: Int) {
        launch {
            var response = userRepository.cancelCollectArticle(id)
            resolveResponse(response, {})
        }
    }
}