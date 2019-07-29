package com.perry.wanandroid.kt.ui.wechat

import androidx.lifecycle.MutableLiveData
import com.perry.wanandroid.kt.PAGE_START
import com.perry.wanandroid.kt.base.BaseViewModel
import com.perry.wanandroid.kt.model.bean.Article
import com.perry.wanandroid.kt.model.bean.SystemParent
import com.perry.wanandroid.kt.model.repository.UserRepository
import com.perry.wanandroid.kt.model.repository.WeChatRepository

class WeChatViewModel : BaseViewModel() {

    private val weChatRepository: WeChatRepository by lazy { WeChatRepository() }
    private val userRepository: UserRepository by lazy { UserRepository() }
    val blogList: MutableLiveData<List<SystemParent>> = MutableLiveData()
    val blogArticle: MutableLiveData<List<Article>> = MutableLiveData()
    val loadMoreBlogArticle: MutableLiveData<List<Article>> = MutableLiveData()
    var page = PAGE_START


    fun getData() {
        getBlogType()
        blogList.value?.get(0)?.id?.let { getBlogArticle(it) }
    }

    fun getBlogType() {
        launch {
            var response = weChatRepository.getBlogType()
            resolveResponse(response, { blogList.value = response.data })
        }
    }

    fun getBlogArticle(id: Int) {
        page = PAGE_START
        launch {
            var response = weChatRepository.getBlogArticle(id, page)
            resolveResponse(response, {
                blogArticle.value = response.data.datas
                page++
            })
        }
    }

    fun loadMoreBlogArticle(id: Int) {
        launch {
            var response = weChatRepository.getBlogArticle(id, page)
            resolveResponse(response, {
                loadMoreBlogArticle.value = response.data.datas
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
