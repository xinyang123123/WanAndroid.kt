package com.perry.wanandroid.kt.ui.me

import androidx.lifecycle.MutableLiveData
import com.perry.wanandroid.kt.base.BaseViewModel
import com.perry.wanandroid.kt.util.UserInfoUtils

class MeViewModel : BaseViewModel() {

    val username: MutableLiveData<String> = MutableLiveData(UserInfoUtils.getUserName())

}
