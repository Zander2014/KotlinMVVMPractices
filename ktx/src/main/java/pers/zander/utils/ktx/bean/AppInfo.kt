package pers.zander.utils.ktx.bean

import android.graphics.drawable.Drawable

/**
 * Created by zander
 * on 2019/6/12 10:54
 */
data class AppInfo(
    val apkPath: String,
    val packageName: String,
    val versionName: String,
    val versionCode: Long,
    val appName: String,
    val icon: Drawable
)