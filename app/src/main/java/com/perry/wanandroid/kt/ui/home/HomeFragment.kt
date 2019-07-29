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

    private var adapter: BaseAdapter<Article> = BaseAdapter(R.layout.item_home_article, null)

    override val layoutId: Int = R.layout.fragment_home

    override fun initBinding() {
        binding.vm = viewModel
        binding.setLifecycleOwner(this)
    }

    override fun initData() {
        initSrl()
        initAdapter()
        initRv()
        viewModel.getHomeArticles()
    }

    override fun providerVmClass(): Class<HomeViewModel>? {
        return HomeViewModel::class.java
    }

    override fun startObserve() {
        super.startObserve()
        viewModel.listData.observe(this, Observer { adapter.setNewData(it) })
        viewModel.loadMoreData.observe(this, Observer { adapter.addData(it) })
    }

    private fun initSrl() {
        srl.setOnRefreshListener { viewModel.getHomeArticles() }
        srl.setOnLoadMoreListener { viewModel.loadMoreHomeArticles() }
    }

    private fun initAdapter() {
        adapter.apply {
            addChildClickEvent(R.id.tv_chapter)
            addChildClickEvent(R.id.tv_super_chapter)
            addChildClickEvent(R.id.iv_collect)
            onItemChildClickListener = itemChildClickListener

            setOnItemClickListener { _, _, position -> startToWebView(position) }
            openLoadAnimation(SCALEIN)
        }
    }

    private fun initRv() {
        rv.adapter = adapter
    }

    private fun startToWebView(position: Int) {
        val article = adapter.getItem(position)

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
            R.id.iv_collect -> collect(item, position)
            else -> {
            }
        }
    }

    private fun collect(item: Article, position: Int) {
        if (!UserInfoUtils.isLogin()) {
            startActivity(Intent(activity, LoginActivity::class.java))
            return
        }

        if (item.collect) {
            viewModel.cancelCollectArticle(item.id)
        } else {
            viewModel.collectArticle(item.id)
        }
        item.collect = !item.collect
        adapter.notifyItemChanged(position)
    }

}