package com.perry.wanandroid.kt

import androidx.databinding.BindingAdapter
import com.perry.wanandroid.kt.model.bean.RequestStatus
import com.scwang.smartrefresh.layout.SmartRefreshLayout

@BindingAdapter("srlStatus")
fun srlStatus(srl: SmartRefreshLayout, status: RequestStatus?) {
    if (status == null) {
        return
    }
    when (status) {
        RequestStatus.START -> srl.autoRefresh()
        RequestStatus.SUCCESS -> srl.finishRefresh(SRL_REFRESH_DELAYED, true)
        RequestStatus.ERROR -> srl.finishRefresh(SRL_REFRESH_DELAYED, false)
        RequestStatus.COMPLETE -> srl.finishRefresh(SRL_REFRESH_DELAYED)
    }
}

@BindingAdapter("loadMoreStatus")
fun loadMoreStatus(srl: SmartRefreshLayout, status: RequestStatus?) {
    if (status == null) {
        return
    }
    when (status) {
        RequestStatus.SUCCESS -> srl.finishLoadMore(true)
        RequestStatus.ERROR -> srl.finishLoadMore(false)
        RequestStatus.COMPLETE -> srl.finishLoadMore()
        else -> {
        }
    }
}