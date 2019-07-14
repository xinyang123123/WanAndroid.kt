package com.perry.wanandroid.kt.ui.home

import android.content.Intent
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.SCALEIN
import com.perry.wanandroid.kt.R
import com.perry.wanandroid.kt.base.BaseAdapter
import com.perry.wanandroid.kt.base.BaseFragment
import com.perry.wanandroid.kt.databinding.FragmentHomeBinding
import com.perry.wanandroid.kt.model.bean.Article
import com.perry.wanandroid.kt.ui.login.LoginActivity
import com.perry.wanandroid.kt.ui.webview.WebActivity
import com.perry.wanandroid.kt.util.UserInfoUtils
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private var adapter: BaseAdapter<Article>? = null

    override val layoutId: Int = R.layout.fragment_home

    override fun initBinding() {
        binding.vm = viewModel
        binding.setLifecycleOwner(this)
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
        viewModel.listData.observe(this, Observer(this::setRv))
        viewModel.loadMoreData.observe(this, Observer(this::loadMore))
    }

    private fun setRv(data: List<Article>) {
        if (adapter == null) {
            adapter = BaseAdapter(R.layout.item_home_article, data).apply {
                addChildClickEvent(R.id.tv_chapter)
                addChildClickEvent(R.id.tv_super_chapter)
                addChildClickEvent(R.id.iv_collect)
                onItemChildClickListener = itemChildClickListener

                setOnItemClickListener { adapter, view, position -> startToWebView(position) }
                openLoadAnimation(SCALEIN)
            }

            rv.adapter = adapter
        } else {
            adapter?.setNewData(data)
        }
    }

    private fun loadMore(data: List<Article>) {
        adapter?.addData(data)
    }

    private fun startToWebView(position: Int) {
        val article = adapter?.getItem(position)

        val intent = Intent(activity, WebActivity::class.java).apply {
            putExtra(WebActivity.KEY_URL, article?.link)
            putExtra(WebActivity.KEY_TITLE, article?.title)
            putExtra(WebActivity.KEY_ID, article?.id)
        }

        activity?.startActivity(intent)
    }

    private val itemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
        var item: Article = adapter.getItem(position) as Article

        when (view.id) {
            R.id.tv_chapter -> {
            }
            R.id.tv_super_chapter -> {
            }
            R.id.iv_collect -> {
                if (UserInfoUtils.isLogin()) {

                } else {
                    startActivity(Intent(activity, LoginActivity::class.java))
                }
            }
            else -> {
            }
        }
    }

}