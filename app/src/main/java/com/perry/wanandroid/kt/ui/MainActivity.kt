package com.perry.wanandroid.kt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.perry.wanandroid.kt.R
import com.perry.wanandroid.kt.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG_HOME = "home"
    val TAG_WECHAT = "wechat"
    val TAG_ME = "me"

    lateinit var fragments: ArrayList<Fragment>
    private lateinit var homeFragment: HomeFragment
    var selected: String = TAG_HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        initNavigation()
    }

    private fun initNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> selectFragment(homeFragment, TAG_HOME)

                R.id.item_weChat -> {
                }

                R.id.item_me -> {
                }
                else -> {
                }
            }
            return@OnNavigationItemSelectedListener true
        })
    }

    private fun initFragment() {
        fragments = ArrayList()
        homeFragment = HomeFragment()
        fragments.add(homeFragment)

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

    private fun startFragment(fragment: Fragment, tag: String) {
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

