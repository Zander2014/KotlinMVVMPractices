package pers.zander.mvvmcore.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Created by zander
 * on 2019/12/27 14:34
 */
@BindingAdapter("isRefresh")
fun SwipeRefreshLayout.isRefresh(isRefresh: Boolean) {
    isRefreshing = isRefresh
}

@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.onRefresh(action: () -> Unit) {
    setOnRefreshListener { action() }
}