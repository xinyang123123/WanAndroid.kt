package com.perry.wanandroid.kt.ui.webview

import android.annotation.SuppressLint
import android.view.MenuItem
import android.view.View
import android.webkit.*
import com.perry.wanandroid.kt.R
import com.perry.wanandroid.kt.base.BaseActivity
import com.perry.wanandroid.kt.databinding.ActivityWebBinding
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity<WebViewModel, ActivityWebBinding>() {

    companion object {
        const val KEY_URL = "KEY_URL"
        const val KEY_TITLE = "KEY_TITLE"
        const val KEY_ID = "KEY_ID"
    }

    override val layoutId: Int = R.layout.activity_web

    private var url: String? = null
    private var title: String? = null
    private var id: Int? = null

    override fun providerVmClass(): Class<WebViewModel>? = null

    override fun initBinding() {
        binding.setLifecycleOwner(this)
    }

    override fun initData() {
        parseIntent()
        initToolbar()
        initWebView()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun parseIntent() {
        url =  intent.getStringExtra(KEY_URL)
        title =  intent.getStringExtra(KEY_TITLE)
        id =  intent.getIntExtra(KEY_ID,0)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        webView.loadUrl(url)
        webView.webChromeClient = webChromeClient
        webView.webViewClient = webViewClient

        webView.settings.javaScriptEnabled = true
        // 排版适应屏幕
        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        // 可任意比例缩放
        webView.settings.useWideViewPort = true
        // 代表不缩放
        webView.setInitialScale(100)
        // 隐藏缩放按钮
        webView.settings.builtInZoomControls = true
        // setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        webView.settings.loadWithOverviewMode = true
        // 支持多窗口
        webView.settings.setSupportMultipleWindows(true)
        webView.settings.setSupportZoom(true)
    }

    private val webChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            if (newProgress < 100) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
            progressBar.progress = newProgress
        }
    }

    private val webViewClient = object :WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }
    }
}
