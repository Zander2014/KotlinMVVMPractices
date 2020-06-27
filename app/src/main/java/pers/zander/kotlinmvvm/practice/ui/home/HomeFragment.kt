package pers.zander.kotlinmvvm.practice.ui.home

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.chad.library.adapter.base.BaseQuickAdapter
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import pers.zander.kotlinmvvm.practice.BR
import pers.zander.kotlinmvvm.practice.R
import pers.zander.kotlinmvvm.practice.adapter.HomeArticleAdapter
import pers.zander.kotlinmvvm.practice.model.bean.Banner
import pers.zander.kotlinmvvm.practice.ui.BrowserActivity
import pers.zander.kotlinmvvm.practice.ui.square.ArticleViewModel
import pers.zander.kotlinmvvm.practice.util.GlideImageLoader
import pers.zander.kotlinmvvm.practice.util.Preference
import pers.zander.kotlinmvvm.practice.view.CustomLoadMoreView
import pers.zander.mvvmcore.base.BaseVMFragment
import pers.zander.utils.ktx.ext.dp2px
import pers.zander.utils.ktx.ext.toast

/**
 * Created by Zander on 2020/6/26.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class HomeFragment: BaseVMFragment<ArticleViewModel>() {
    override fun getLayoutResId(): Int = R.layout.fragment_home

    override fun initVM(): ArticleViewModel = getViewModel()

    private val isLogin by Preference(Preference.IS_LOGIN, false)
    private val homeArticleAdapter by lazy { HomeArticleAdapter() }
    private val bannerImages = mutableListOf<String>()
    private val bannerTitles = mutableListOf<String>()
    private val bannerUrls = mutableListOf<String>()
    private val banner by lazy { com.youth.banner.Banner(activity) }

    override fun initView() {
        mBinding.run {
            setVariable(BR.viewModel, mViewModel)
            setVariable(BR.adapter, homeArticleAdapter)
        }
        initRecyclerView()
        initBanner()
    }

    private fun initBanner() {
        banner.run{
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, banner.dp2px(200))
            setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            setImageLoader(GlideImageLoader())
            setOnBannerListener{position ->
                Navigation.findNavController(banner).navigate(R.id.action_tab_to_browser, bundleOf(BrowserActivity.URL to bannerUrls[position]))
            }
        }
    }

    private fun initRecyclerView() {
        homeArticleAdapter.run{
            setOnItemClickListener{_,_,position ->
                val bundle = bundleOf(BrowserActivity.URL to homeArticleAdapter.data[position].link)
                NavHostFragment.findNavController(this@HomeFragment).navigate(R.id.action_tab_to_browser, bundle)
            }
            onItemChildClickListener = this@HomeFragment.onItemChildClickListener
            if(headerLayoutCount > 0) removeAllHeaderView()
            addHeaderView(banner)
            setLoadMoreView(CustomLoadMoreView())
            setOnLoadMoreListener({loadMore()}, homeRecycleView)
        }
    }

    private fun loadMore() {
        mViewModel.getHomeArticleList(false)
    }

    private val onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener{_,view,position->
        when(view.id){
            R.id.articleStar->{
                if(isLogin){
                    homeArticleAdapter.run {
                        data[position].run {
                            collect = !collect
                            mViewModel.collectArticle(id, collect)
                        }
                        notifyItemChanged(position)
                    }
                }else{
                    Navigation.findNavController(homeRecycleView).navigate(R.id.action_tab_to_login)
                }
            }
        }
    }

    override fun initData() {
        refresh()
    }

    private fun refresh() {
        mViewModel.getHomeArticleList(true)
    }

    override fun startObserve() {
        mViewModel.apply {
            banners.observe(viewLifecycleOwner, Observer {
                it?.let { setBanner(it) }
            })
            uiState.observe(viewLifecycleOwner, Observer {
                it.showSuccess?.let {list->
                    homeArticleAdapter.run {
                        setEnableLoadMore(false)
                        if(it.isRefresh) replaceData(list.datas)
                        else addData(list.datas)
                        setEnableLoadMore(true)
                        loadMoreComplete()
                    }
                }
                if(it.showEnd) homeArticleAdapter.loadMoreEnd()

                it.showError?.let { message ->
                    activity?.toast(if (message.isBlank()) "网络异常" else message)
                }
            })
        }
    }

    private fun setBanner(banners : List<Banner> ) {
        for(banner in banners){
            bannerImages.add(banner.imagePath)
            bannerTitles.add(banner.title)
            bannerUrls.add(banner.url)
        }
        banner.setImages(bannerImages)
            .setBannerTitles(bannerTitles)
            .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            .setDelayTime(3000)
        banner.start()
    }

    override fun onStart() {
        super.onStart()
        banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        banner.stopAutoPlay()
    }
}