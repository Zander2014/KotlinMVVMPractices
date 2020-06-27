package pers.zander.kotlinmvvm.practice.util

import android.app.Activity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import pers.zander.kotlinmvvm.practice.model.bean.SystemChild
import pers.zander.kotlinmvvm.practice.model.bean.WanResponse
import pers.zander.utils.ktx.ext.toast
import retrofit2.HttpException

/**
 * Created by Zander on 2020/6/26.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
const val TOOL_URL = "http://www.wanandroid.com/tools"
const val GITHUB_PAGE = "https://github.com/Zander2014/KotlinMVVMPractices"
const val ISSUE_URL = "https://github.com/Zander2014/KotlinMVVMPractices/issues"

suspend fun executeResponse(response: WanResponse<Any>, successBlock: suspend CoroutineScope.()->Unit, errorBlock: suspend CoroutineScope.()->Unit){
    coroutineScope{
        if(response.errorCode == -1) errorBlock()
        else successBlock()
    }
}

fun Activity.OnNetError(e: Throwable, func: (e: Throwable) -> Unit){
    if(e is HttpException){
        toast("网络异常")
        func(e)
    }
}

fun WanResponse<Any>.isSuccess(): Boolean = this.errorCode == 0

fun transformSystemChild(children: List<SystemChild>): String{
    return children.joinToString("     ", transform = {child -> child.name})
}