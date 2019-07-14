package com.perry.wanandroid.kt.ui.me

import com.perry.wanandroid.kt.R
import com.perry.wanandroid.kt.base.BaseFragment
import com.perry.wanandroid.kt.databinding.MeFragmentBinding

class MeFragment : BaseFragment<MeViewModel, MeFragmentBinding>() {
    override val layoutId: Int = R.layout.me_fragment

    override fun initBinding() {
        binding.apply {
            setLifecycleOwner(this@MeFragment)
            viewModel = this@MeFragment.viewModel
        }
    }

    override fun initData() {
    }

    override fun providerVmClass(): Class<MeViewModel>? = MeViewModel::class.java

    override fun startObserve() {

    }

}
