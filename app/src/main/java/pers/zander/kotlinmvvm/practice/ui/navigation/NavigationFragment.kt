package pers.zander.kotlinmvvm.practice.ui.navigation

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_navigation.*
import kotlinx.android.synthetic.main.item_navigation.view.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import pers.zander.kotlinmvvm.practice.BR
import pers.zander.kotlinmvvm.practice.R
import pers.zander.kotlinmvvm.practice.adapter.NavigationAdapter
import pers.zander.kotlinmvvm.practice.adapter.VerticalTabAdapter
import pers.zander.kotlinmvvm.practice.databinding.FragmentNavigationBinding
import pers.zander.kotlinmvvm.practice.model.bean.Navigation
import pers.zander.mvvmcore.base.BaseVMFragment
import pers.zander.utils.ktx.ext.dp2px
import q.rorbin.verticaltablayout.VerticalTabLayout
import q.rorbin.verticaltablayout.widget.TabView

/**
 * Created by Zander on 2020/6/25.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class NavigationFragment : BaseVMFragment<NavigationViewModel>() {
    override fun getLayoutResId(): Int = R.layout.fragment_navigation

    private val navigationList = mutableListOf<Navigation>()
    private val tabAdapter by lazy { VerticalTabAdapter(navigationList.map { it.name }) }
    private val navigationAdapter by lazy { NavigationAdapter() }

    override fun initVM(): NavigationViewModel = getViewModel()

    override fun initView() {
        mBinding.setVariable(BR.adapter, navigationAdapter)
        initTabLayout()
    }

    private fun initTabLayout() {
        tabLayout.addOnTabSelectedListener(object : VerticalTabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabView?, position: Int) {
            }

            override fun onTabSelected(tab: TabView?, position: Int) {
                scrollToPosition(position)
            }
        })
    }

    private fun scrollToPosition(position: Int) {
        val mLayoutManager = navigationRecycleView.layoutManager as LinearLayoutManager
        val firstPosition = mLayoutManager.findFirstVisibleItemPosition()
        val lastPosition = mLayoutManager.findLastVisibleItemPosition()
        when{
            position <= firstPosition || position >= lastPosition -> navigationRecycleView.smoothScrollToPosition(position)
            else -> navigationRecycleView.run {
                smoothScrollBy(0, this.getChildAt(position - firstPosition).top - this.dp2px(8))
            }
        }
    }

    override fun initData() = mViewModel.getNavigation()

    private fun getNavigation(navigationList: List<Navigation>) {
        this.navigationList.clear()
        this.navigationList.addAll(navigationList)
        tabLayout.setTabAdapter(tabAdapter)

        navigationAdapter.setNewData(navigationList)
    }

    override fun startObserve() {
        mViewModel.run {
            uiState.observe(viewLifecycleOwner, Observer {
                it?.let { getNavigation(it) }
            })
        }
    }
}