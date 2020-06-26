package pers.zander.kotlinmvvm.practice.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_bottom_navigation.*
import pers.zander.kotlinmvvm.practice.R
import pers.zander.kotlinmvvm.practice.ui.main.MainFragment
import pers.zander.kotlinmvvm.practice.ui.profile.ProfileFragment
import pers.zander.kotlinmvvm.practice.ui.project.BlogFragment
import pers.zander.kotlinmvvm.practice.ui.project.ProjectFragment
import pers.zander.kotlinmvvm.practice.ui.search.SearchFragment
import pers.zander.mvvmcore.base.BaseFragment

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 * 这是首页Tab
 */
class TabFragment : BaseFragment() {
    private val fragmentList = arrayListOf<Fragment>()
    private val mainFragment by lazy { MainFragment() }
    private val blogFragment by lazy { BlogFragment() }
    private val searchFragment by lazy { SearchFragment() }
    private val projectFragment by lazy { ProjectFragment() }
    private val profileFragment by lazy { ProfileFragment() }

    init {
        fragmentList.run {
            add(mainFragment)
        }
    }

    override fun getLayoutResId(): Int = R.layout.activity_bottom_navigation

    override fun initView() {
        initViewPager()
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener{
        when(it.itemId){
            R.id.home ->{
                switchFragment(0)
            }
            R.id.blog ->{
                switchFragment(1)
            }
            R.id.search ->{
                switchFragment(2)
            }
            R.id.project ->{
                switchFragment(3)
            }
            R.id.profile ->{
                switchFragment(4)
            }
        }
        true
    }

    private fun switchFragment(index: Int){
        mainViewpager.setCurrentItem(index, false)
    }

    private fun initViewPager() {
        mainViewpager.isUserInputEnabled = false
        mainViewpager.offscreenPageLimit = 2
        mainViewpager.adapter = object : FragmentStateAdapter(this){
            override fun getItemCount(): Int = fragmentList.size

            override fun createFragment(position: Int): Fragment = fragmentList[position]
        }
    }

    override fun initData() {
    }
}