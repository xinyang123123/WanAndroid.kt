package com.perry.wanandroid.kt.ui

import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.perry.wanandroid.kt.R
import com.perry.wanandroid.kt.base.BaseActivity
import com.perry.wanandroid.kt.base.BaseViewModel
import com.perry.wanandroid.kt.ui.home.HomeFragment
import com.perry.wanandroid.kt.ui.me.MeFragment
import com.perry.wanandroid.kt.ui.wechat.WeChatFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<BaseViewModel, com.perry.wanandroid.kt.databinding.ActivityMainBinding>() {

    val TAG_HOME = "home"
    val TAG_WECHAT = "wechat"
    val TAG_ME = "me"

    private var fragments: ArrayList<Fragment> = ArrayList()
    private var homeFragment: HomeFragment = HomeFragment()
    private var weChatFragment: WeChatFragment = WeChatFragment()
    private var meFragment: MeFragment = MeFragment()
    var selected: String = TAG_HOME

    override val layoutId: Int = R.layout.activity_main

    override fun providerVmClass(): Class<BaseViewModel>? = BaseViewModel::class.java

    override fun initBinding() {
    }

    override fun initData() {
        initFragment()
        initNavigation()
    }

    private fun initNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> selectFragment(homeFragment, TAG_HOME)
                R.id.item_weChat -> selectFragment(weChatFragment, TAG_WECHAT)
                R.id.item_me -> selectFragment(meFragment, TAG_ME)
                else -> {
                }
            }
            return@OnNavigationItemSelectedListener true
        })
    }

    private fun initFragment() {
        fragments.add(homeFragment)
        fragments.add(weChatFragment)
        fragments.add(meFragment)

        selectFragment(homeFragment, TAG_HOME)
    }

    private fun selectFragment(fragment: Fragment, tag: String) {
        selected = tag

        startFragment(fragment, tag)
        showFragment(tag)
    }

    private fun showFragment(tag: String) {
        val transaction = supportFragmentManager.beginTransaction()

        for (fragment in fragments) {
            if (fragment.isAdded) {
                if (tag == fragment.tag) {
                    transaction.show(fragment)
                } else {
                    transaction.hide(fragment)
                }
            }
        }
        transaction.commit()
    }
}

