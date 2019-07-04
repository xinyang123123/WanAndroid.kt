package com.perry.wanandroid.kt

import androidx.databinding.BindingAdapter
import com.perry.wanandroid.kt.model.bean.RequestStatus
import com.scwang.smartrefresh.layout.SmartRefreshLayout

@BindingAdapter("srlStatus")
fun SmartRefreshLayout.srlStatus(status: RequestStatus?) {
    when (status) {
        RequestStatus.START -> autoRefresh()
        RequestStatus.SUCCESS -> finishRefresh(SRL_REFRESH_DELAYED, true)
        RequestStatus.ERROR -> finishRefresh(SRL_REFRESH_DELAYED, false)
        RequestStatus.COMPLETE -> finishRefresh(SRL_REFRESH_DELAYED)
    }
}

@BindingAdapter("loadMoreStatus")
fun loadMoreStatus(smartRefreshLayout: SmartRefreshLayout,status: RequestStatus?) {
    when (status) {
        RequestStatus.SUCCESS -> smartRefreshLayout.finishLoadMore(true)
        RequestStatus.ERROR -> smartRefreshLayout.finishLoadMore(false)
        RequestStatus.COMPLETE -> smartRefreshLayout.finishLoadMore()
        else -> {
        }
    }
}