package com.perry.wanandroid.kt.model.repository

import com.perry.wanandroid.kt.base.BaseRepository
import com.perry.wanandroid.kt.model.bean.Response
import com.perry.wanandroid.kt.model.bean.User
import com.perry.wanandroid.kt.model.romote.WanRetrofitClient

class LoginRepository : BaseRepository() {

    suspend fun login(account: String, password: String): Response<User> {
        return apiCall { WanRetrofitClient.service.login(account, password) }
    }

}