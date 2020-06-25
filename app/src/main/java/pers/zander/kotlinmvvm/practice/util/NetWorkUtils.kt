package pers.zander.kotlinmvvm.practice.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class NetWorkUtils {
    companion object{
        fun isNetworkAvailable(context: Context): Boolean{
            val manager = context.applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = manager.activeNetworkInfo
            return !(null == info || !info.isAvailable)
        }
    }
}