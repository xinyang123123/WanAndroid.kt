package com.perry.wanandroid.kt.model.bean

data class Response<T>(val data: T, val errorCode: Int, val errorMsg: String)