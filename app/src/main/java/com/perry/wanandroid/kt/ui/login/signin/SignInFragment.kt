package com.perry.wanandroid.kt.ui.login.signin

import com.perry.wanandroid.kt.R
import com.perry.wanandroid.kt.base.BaseFragment
import com.perry.wanandroid.kt.databinding.SignInFragmentBinding

class SignInFragment : BaseFragment<SignInViewModel, SignInFragmentBinding>() {
    override val layoutId: Int = R.layout.sign_in_fragment

    override fun initBinding() {
        binding.apply {
            setLifecycleOwner(this@SignInFragment)
            viewModel = this@SignInFragment.viewModel
        }
    }

    override fun initData() {
    }

    override fun providerVmClass(): Class<SignInViewModel>? = SignInViewModel::class.java

    override fun startObserve() {
    }

}
