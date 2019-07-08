package com.perry.wanandroid.kt

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.perry.wanandroid.kt.model.bean.RequestStatus
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import java.text.SimpleDateFormat
import java.util.*

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
fun SmartRefreshLayout.loadMoreStatus(status: RequestStatus?) {
    when (status) {
        RequestStatus.SUCCESS -> finishLoadMore(true)
        RequestStatus.ERROR -> finishLoadMore(false)
        RequestStatus.COMPLETE -> finishLoadMore()
        else -> {
        }
    }
}

@BindingAdapter("visible")
fun setVisible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("publishTime")
fun publishTime(textView: TextView, time: Long) {
    textView.text = SimpleDateFormat("yyyy-MM-dd").format(Date(time))
}
