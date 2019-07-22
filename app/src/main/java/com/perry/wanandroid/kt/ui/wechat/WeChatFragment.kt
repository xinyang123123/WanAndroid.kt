package com.perry.wanandroid.kt.ui.wechat

import androidx.lifecycle.Observer
import com.perry.wanandroid.kt.R
import com.perry.wanandroid.kt.base.BaseFragment
import com.perry.wanandroid.kt.databinding.FragmentWechatBinding
import com.perry.wanandroid.kt.model.bean.SystemParent

class WeChatFragment : BaseFragment<WeChatViewModel, FragmentWechatBinding>() {
    override val layoutId: Int = R.layout.fragment_wechat

    override fun initBinding() {
        binding.setLifecycleOwner(this)
        binding.viewModel = this@WeChatFragment.viewModel
    }

    override fun initData() {
        viewModel.getData()
    }

    override fun providerVmClass(): Class<WeChatViewModel>? = WeChatViewModel::class.java

    override fun startObserve() {
        viewModel.blogList.observe(this, Observer { initTabLayout(it) })
    }

    private fun initTabLayout(data: List<SystemParent>) {

    }

    private fun initVp() {

    }
}
