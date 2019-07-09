package com.perry.wanandroid.kt.ui.login

import com.perry.wanandroid.kt.R
import com.perry.wanandroid.kt.base.BaseActivity
import com.perry.wanandroid.kt.base.BaseViewModel
import com.perry.wanandroid.kt.databinding.LoginActivityBinding


class LoginActivity : BaseActivity<BaseViewModel, LoginActivityBinding>() {
    override val layoutId: Int = R.layout.login_activity

    override fun providerVmClass(): Class<BaseViewModel>? = null


    override fun initBinding() {
    }

    override fun initData() {
    }


}
