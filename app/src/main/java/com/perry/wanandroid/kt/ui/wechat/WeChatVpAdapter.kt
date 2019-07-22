package com.perry.wanandroid.kt.ui.wechat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.perry.wanandroid.kt.model.bean.SystemParent

class WeChatVpAdapter(fragmentManager: FragmentManager, private val data: List<SystemParent>) :
        FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return WeChatListFragment(data[position])
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return data[position].name
    }
}