package com.perry.wanandroid.kt.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.perry.wanandroid.kt.BR

class BaseAdapter<T>(itemLayoutId: Int, data: List<T>) : BaseQuickAdapter<T, BindingViewHolder>(itemLayoutId, data) {

    private var childViewClickListenerList: ArrayList<Int> = ArrayList()

    override fun createBaseViewHolder(parent: ViewGroup?, layoutResId: Int): BindingViewHolder {
        return BindingViewHolder(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(mContext),
                layoutResId, parent, false))
    }

    override fun convert(helper: BindingViewHolder?, item: T) {
        helper?.dataBinding?.setVariable(BR.data, item)

        childViewClickListenerList.forEach { helper?.addOnClickListener(it) }
    }

    public fun addChildClickEvent(childId: Int) {
        childViewClickListenerList.add(childId)
    }
}