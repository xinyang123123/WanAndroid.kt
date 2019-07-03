package com.perry.wanandroid.kt.base

import com.perry.wanandroid.kt.model.bean.Response

open class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Response<T> {
        return call.invoke()
    }
}