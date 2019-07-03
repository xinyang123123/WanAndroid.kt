package com.perry.wanandroid.kt.ui.home

import androidx.lifecycle.MutableLiveData
import com.perry.wanandroid.kt.PAGE_START
import com.perry.wanandroid.kt.base.BaseViewModel
import com.perry.wanandroid.kt.model.bean.Article
import com.perry.wanandroid.kt.model.bean.RequestStatus
import com.perry.wanandroid.kt.model.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel : BaseViewModel() {

    private val repository by lazy { HomeRepository() }

    var listData: MutableLiveData<List<Article>> = MutableLiveData()
    var loadMoreData: MutableLiveData<List<Article>> = MutableLiveData()
    var page = PAGE_START

    fun getHomeArticles() {
        launch {
            page = PAGE_START
            val result = withContext(Dispatchers.IO) { repository.getHomeArticles(page) }
            resolveResponse(result, {
                page++
                listData.value = result.data.datas
            }, {})
        }
    }

    fun loadMoreHomeArticles() {
        launch {
            val result = withContext(Dispatchers.IO) { repository.getHomeArticles(page) }
            resolveResponse(result, {
                if (result.data.over) loadMoreStatus.value = RequestStatus.COMPLETE
                loadMoreData.value = result.data.datas
                page++
            }, {})
        }
    }


}