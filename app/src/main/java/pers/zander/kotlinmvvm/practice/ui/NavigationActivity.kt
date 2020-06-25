package pers.zander.kotlinmvvm.practice.ui

import android.os.Bundle
import pers.zander.kotlinmvvm.practice.R
import pers.zander.mvvmcore.base.BaseActivity

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class NavigationActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
    }

    override fun initData() {
    }
}