package com.perry.wanandroid.kt.ui.login

import android.text.TextUtils
import androidx.lifecycle.Observer
import com.perry.wanandroid.kt.R
import com.perry.wanandroid.kt.base.BaseActivity
import com.perry.wanandroid.kt.databinding.LoginActivityBinding


class LoginActivity : BaseActivity<LogInViewModel, LoginActivityBinding>() {
    override val layoutId: Int = R.layout.login_activity

    override fun providerVmClass(): Class<LogInViewModel>? = LogInViewModel::class.java


    override fun initBinding() {
        binding.apply {
            setLifecycleOwner(this@LoginActivity)
            activity = this@LoginActivity
            viewModel = this@LoginActivity.viewModel
        }
    }

    override fun initData() {

    }

    override fun startObserve() {
        super.startObserve()
        viewModel.isLoginSucceed.observe(this, Observer {
            if (it) {
                showToast("登陆成功")
                finish()
            }
        })
    }

    fun verifyInput() {
        if (TextUtils.isEmpty(viewModel.account.value)) {
            showToast("请输入账号")
            return
        }

        if (TextUtils.isEmpty(viewModel.password.value)) {
            showToast("请输入密码")
            return
        }

        viewModel.login()
    }

}
