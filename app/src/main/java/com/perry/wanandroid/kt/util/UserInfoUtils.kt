package com.perry.wanandroid.kt.util

import com.blankj.utilcode.util.SPUtils
import com.perry.wanandroid.kt.SP_IS_LOGIN
import com.perry.wanandroid.kt.SP_USERNAME
import com.perry.wanandroid.kt.model.bean.User

object UserInfoUtils {

    fun isLogin(): Boolean {
        return SPUtils.getInstance().getBoolean(SP_IS_LOGIN, false)
    }

    fun setLoginState(status: Boolean) {
        return SPUtils.getInstance().put(SP_IS_LOGIN, status)
    }

    fun getUserName(): String {
        return SPUtils.getInstance().getString(SP_USERNAME)
    }

    fun saveUserInfo(user: User) {
        setLoginState(true)
        SPUtils.getInstance().put(SP_USERNAME, user.username)
    }

    fun clearUserInfo() {
        SPUtils.getInstance().put(SP_IS_LOGIN, false)
        SPUtils.getInstance().put(SP_USERNAME, "")
    }
}