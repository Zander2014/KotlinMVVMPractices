package pers.zander.kotlinmvvm.practice.ui.main

import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*
import pers.zander.kotlinmvvm.practice.R
import pers.zander.kotlinmvvm.practice.ui.home.HomeFragment
import pers.zander.kotlinmvvm.practice.ui.navigation.NavigationFragment
import pers.zander.kotlinmvvm.practice.ui.project.LastProjectFragment
import pers.zander.kotlinmvvm.practice.ui.square.SquareFragment
import pers.zander.kotlinmvvm.practice.ui.system.SystemFragment
import pers.zander.kotlinmvvm.practice.util.Preference
import pers.zander.mvvmcore.base.BaseFragment

/**
 * Created by Zander on 2020/6/26.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class MainFragment: BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_main

    private var isLogin by Preference(Preference.IS_LOGIN, false)

    private val titleList = arrayOf("首页", "广场", "最新项目", "体系", "导航")
    private val fragmentList = arrayListOf<Fragment>()
    private val homeFragment by lazy { HomeFragment() }
    private val squareFragment by lazy { SquareFragment() }
    private val lastProjectFragment by lazy { LastProjectFragment.newInstance(0, true) }
    private val systemFragment by lazy { SystemFragment() }
    private val navigationFragment by lazy { NavigationFragment() }
    private val mOnPageChangeCallback: ViewPager2.OnPageChangeCallback? = null

    init {
        fragmentList.add(homeFragment)
        fragmentList.add(squareFragment)
        fragmentList.add(lastProjectFragment)
        fragmentList.add(systemFragment)
        fragmentList.add(navigationFragment)
    }

    override fun initView() {
        initViewPager()
        addFab.setOnClickListener{
            if(!isLogin) Navigation.findNavController(viewPager).navigate(R.id.action_tab_to_login)
            else Navigation.findNavController(viewPager).navigate(R.id.action_tab_to_share)
        }
    }

    private fun initViewPager() {
        viewPager.offscreenPageLimit = 1
        viewPager.adapter = object : FragmentStateAdapter(this){
            override fun getItemCount(): Int = titleList.size

            override fun createFragment(position: Int): Fragment = fragmentList[position]
        }

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titleList[position]
        }.attach()
    }

    override fun initData() {
    }

    override fun onResume() {
        super.onResume()
        if(mOnPageChangeCallback == null) object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if(position == 1) addFab.show() else addFab.hide()
            }
        }
        mOnPageChangeCallback?.let { viewPager.registerOnPageChangeCallback(it) }
    }

    override fun onStop() {
        super.onStop()
        mOnPageChangeCallback?.let { viewPager.unregisterOnPageChangeCallback(it) }
    }
}