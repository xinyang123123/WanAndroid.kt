package com.perry.wanandroid.kt.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> : Fragment() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initViewModel()
        startObserve()
        initData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return binding.root
    }

    abstract val layoutId: Int

    abstract fun initBinding()

    abstract fun initData()

    abstract fun providerVmClass(): Class<VM>?

    abstract fun startObserve()

    private fun initViewModel() {
        providerVmClass()?.let {
            viewModel = ViewModelProviders.of(this).get(it)
            lifecycle.run { addObserver(viewModel) }
        }
    }
}