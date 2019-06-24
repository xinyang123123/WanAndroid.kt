package com.perry.wanandroid.kt.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayoutId())
        initBinding()
        initViewModel()
        startObserve()
        initData()
    }

    abstract fun getLayoutId(): Int

    abstract fun initData()

    open fun initBinding() {}

    open fun providerVmClass(): Class<VM>? = null

    open fun startObserve() {}

    private fun initViewModel() {
        providerVmClass()?.let {
            viewModel = ViewModelProviders.of(this).get(it)
            lifecycle.run { addObserver(viewModel) }
        }
    }
}