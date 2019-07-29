package com.perry.wanandroid.kt.ui.login

import androidx.lifecycle.MutableLiveData
import com.perry.wanandroid.kt.base.BaseViewModel
import com.perry.wanandroid.kt.model.bean.RequestStatus
import com.perry.wanandroid.kt.model.repository.UserRepository
import com.perry.wanandroid.kt.util.UserInfoUtils

class LogInViewModel : BaseViewModel() {

    private val repository by lazy { UserRepository() }
    val account: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val isShowPassword: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoginSucceed: MutableLiveData<Boolean> = MutableLiveData(false)

    fun switchPwdVisibility() {
        //TODO add listener
        isShowPassword.value = !isShowPassword.value!!
    }

    fun login() {
        launch {
            requestStatus.value = RequestStatus.START
            var result = repository.login(account.value!!, password.value!!)
            resolveResponse(result, {
                UserInfoUtils.saveUserInfo(result.data)
                isLoginSucceed.value = true
            })
        }
    }
}
