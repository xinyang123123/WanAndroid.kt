package com.perry.wanandroid.kt

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
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
fun View.setVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("publishTime")
fun publishTime(textView: TextView, time: Long) {
    textView.text = SimpleDateFormat("yyyy-MM-dd").format(Date(time))
}

@BindingAdapter("clearInput")
fun View.clearInput(content: MutableLiveData<String>) {
    setOnClickListener { content.value = "" }
}

@BindingAdapter("visibleByString")
fun View.setVisible(string: String?) {
    visibility = if (TextUtils.isEmpty(string)) View.GONE else View.VISIBLE
}
