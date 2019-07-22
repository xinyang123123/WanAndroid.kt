package com.perry.wanandroid.kt.ui.wechat

import androidx.lifecycle.Observer
import com.perry.wanandroid.kt.R
import com.perry.wanandroid.kt.base.BaseFragment
import com.perry.wanandroid.kt.databinding.FragmentWechatListBinding
import com.perry.wanandroid.kt.model.bean.SystemParent
import kotlinx.android.synthetic.main.fragment_home.*

class WeChatListFragment(private val typeInfo: SystemParent) : BaseFragment<WeChatViewModel, FragmentWechatListBinding>() {

    override val layoutId: Int = R.layout.fragment_wechat_list

    override fun initBinding() {
        binding.setLifecycleOwner(this)
        binding.viewModel = this.viewModel
    }

    override fun initData() {
        initSrl()
        viewModel.getBlogArticle(typeInfo.id)
    }

    override fun providerVmClass(): Class<WeChatViewModel>? = WeChatViewModel::class.java

    override fun startObserve() {
        viewModel.blogArticle.observe(this, Observer { })
        viewModel.loadMoreBlogArticle.observe(this, Observer { })
    }

    private fun initSrl() {
        srl.setOnRefreshListener { viewModel.getBlogArticle(typeInfo.id) }
        srl.setOnLoadMoreListener { viewModel.loadMoreBlogArticle(typeInfo.id) }
    }

    private fun initRv() {

    }

}