package com.perry.wanandroid.kt.ui.wechat

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.perry.wanandroid.kt.R
import com.perry.wanandroid.kt.base.BaseAdapter
import com.perry.wanandroid.kt.base.BaseFragment
import com.perry.wanandroid.kt.databinding.FragmentWechatListBinding
import com.perry.wanandroid.kt.model.bean.Article
import com.perry.wanandroid.kt.model.bean.SystemParent
import com.perry.wanandroid.kt.ui.login.LoginActivity
import com.perry.wanandroid.kt.ui.webview.WebActivity
import com.perry.wanandroid.kt.util.UserInfoUtils
import kotlinx.android.synthetic.main.fragment_home.*

class WeChatListFragment(private val typeInfo: SystemParent) :
    BaseFragment<WeChatViewModel, FragmentWechatListBinding>() {

    override val layoutId: Int = R.layout.fragment_wechat_list
    private val adapter: BaseAdapter<Article> = BaseAdapter(R.layout.item_wechat_article, null)

    override fun initBinding() {
        binding.setLifecycleOwner(this)
        binding.viewModel = this.viewModel
    }

    override fun initData() {
        initSrl()
        initAdapter()
        initRv()
        viewModel.getBlogArticle(typeInfo.id)
    }

    override fun providerVmClass(): Class<WeChatViewModel>? = WeChatViewModel::class.java

    override fun startObserve() {
        viewModel.blogArticle.observe(this, Observer { adapter.setNewData(it) })
        viewModel.loadMoreBlogArticle.observe(this, Observer { adapter.addData(it) })
    }

    private fun initSrl() {
        srl.setOnRefreshListener { viewModel.getBlogArticle(typeInfo.id) }
        srl.setOnLoadMoreListener { viewModel.loadMoreBlogArticle(typeInfo.id) }
    }

    private fun initAdapter() {
        adapter.apply {
            addChildClickEvent(R.id.iv_collect)
            setOnItemChildClickListener(::itemChildClick)
            setOnItemClickListener { _, _, position -> startToWebView(position) }
            openLoadAnimation(BaseQuickAdapter.SCALEIN)
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

    private fun itemChildClick(adapter: BaseQuickAdapter<Any, BaseViewHolder>, view: View, position: Int) {
        var item = adapter.getItem(position) as Article
        when (view.id) {
            R.id.iv_collect -> {
                collectArticle(item, position)
            }
        }
    }

    private fun collectArticle(item: Article, position: Int) {
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