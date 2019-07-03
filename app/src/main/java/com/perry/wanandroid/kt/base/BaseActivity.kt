package com.perry.wanandroid.kt.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.perry.wanandroid.kt.R

abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
        initBinding()
        initViewModel()
        startObserve()
        initData()
    }

    abstract val layoutId: Int

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

    protected fun startFragment(fragment: Fragment, tag: String) {
        if (fragment.isAdded) {
            supportFragmentManager.beginTransaction()
                    .show(fragment)
                    .commit()
        } else {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fl_content, fragment, tag)
                    .commit()
        }
    }
}