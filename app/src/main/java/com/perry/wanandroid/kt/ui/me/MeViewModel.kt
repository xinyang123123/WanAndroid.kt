package com.perry.wanandroid.kt.ui.me

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.perry.wanandroid.kt.base.BaseViewModel
import com.perry.wanandroid.kt.util.UserInfoUtils

class MeViewModel : BaseViewModel() {

    val username: MutableLiveData<String> = MutableLiveData(UserInfoUtils.getUserName())

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun refreshUserInfo() {
        username.value = UserInfoUtils.getUserName()
    }

    fun logout() {
        UserInfoUtils.clearUserInfo()
        refreshUserInfo()
    }
}
