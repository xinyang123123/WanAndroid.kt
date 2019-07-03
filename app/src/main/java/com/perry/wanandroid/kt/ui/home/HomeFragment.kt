package com.perry.wanandroid.kt.ui.home

import androidx.lifecycle.Observer
import com.perry.wanandroid.kt.R
import com.perry.wanandroid.kt.base.BaseAdapter
import com.perry.wanandroid.kt.base.BaseFragment
import com.perry.wanandroid.kt.databinding.FragmentHomeBinding
import com.perry.wanandroid.kt.model.bean.Article
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private var adapter: BaseAdapter<Article>? = null

    override val layoutId: Int = R.layout.fragment_home

    override fun initBinding() {
        binding.vm = viewModel
    }

    override fun initData() {
        srl.setOnRefreshListener { viewModel.getHomeArticles() }
        srl.setOnLoadMoreListener { viewModel.loadMoreHomeArticles() }
        viewModel.getHomeArticles()
    }

    override fun providerVmClass(): Class<HomeViewModel>? {
        return HomeViewModel::class.java
    }

    override fun startObserve() {
        viewModel.listData.observe(this, Observer(this::initRv))
        viewModel.loadMoreData.observe(this, Observer(this::loadMore))
    }

    private fun initRv(data: List<Article>) {
        if (adapter == null) {
            adapter = BaseAdapter(R.layout.item_home_article, data)
            rv.adapter = adapter
        } else {
            adapter?.setNewData(data)
        }
    }

    private fun loadMore(data: List<Article>){
        adapter?.addData(data)
    }

}