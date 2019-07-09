package com.perry.wanandroid.kt.ui.wechat

import com.perry.wanandroid.kt.R
import com.perry.wanandroid.kt.base.BaseFragment
import com.perry.wanandroid.kt.databinding.WeChatFragmentBinding

class WeChatFragment : BaseFragment<WeChatViewModel, WeChatFragmentBinding>() {
    override val layoutId: Int = R.layout.we_chat_fragment

    override fun initBinding() {
        binding.setLifecycleOwner(this)
    }

    override fun initData() {

    }

    override fun providerVmClass(): Class<WeChatViewModel>? = WeChatViewModel::class.java

    override fun startObserve() {

    }

}
