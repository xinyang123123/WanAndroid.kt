package com.perry.wanandroid.kt.ui.me

import android.app.AlertDialog
import android.content.Intent
import com.perry.wanandroid.kt.R
import com.perry.wanandroid.kt.base.BaseFragment
import com.perry.wanandroid.kt.databinding.FragmentMeBinding
import com.perry.wanandroid.kt.ui.login.LoginActivity

class MeFragment : BaseFragment<MeViewModel, FragmentMeBinding>() {
    override val layoutId: Int = R.layout.fragment_me

    override fun initBinding() {
        binding.apply {
            setLifecycleOwner(this@MeFragment)
            viewModel = this@MeFragment.viewModel
            fragment = this@MeFragment
        }
    }

    override fun initData() {
    }

    override fun providerVmClass(): Class<MeViewModel>? = MeViewModel::class.java

    override fun startObserve() {

    }

    fun startToLogin() {
        startActivity(Intent(activity, LoginActivity::class.java))
    }

    fun logout() {
        AlertDialog.Builder(activity)
                .setTitle("是否退出登陆？")
                .setPositiveButton("OK") { _, _ -> viewModel.logout() }
                .setNegativeButton("Cancel") { _, _ -> }
                .show()
    }

}
