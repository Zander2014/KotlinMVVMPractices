package pers.zander.kotlinmvvm.practice.ui.share

import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_share.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import pers.zander.kotlinmvvm.practice.R
import pers.zander.kotlinmvvm.practice.databinding.ActivityShareBinding
import pers.zander.mvvmcore.base.BaseVMActivity
import pers.zander.utils.ktx.ext.toast

/**
 * Created by Zander on 2020/6/25.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class ShareActivity: BaseVMActivity<ShareViewModel>() {

    override fun getLayoutResId() = R.layout.activity_share

    override fun initVM(): ShareViewModel = getViewModel()

    override fun initView() {
        (mBinding as ActivityShareBinding).viewModel = mViewModel
    }

    override fun initData() {}

    override fun startObserve() {
        mViewModel.uiState.observe(this, Observer {
            it.showSuccess?.let {
                Navigation.findNavController(shareBt).navigateUp()
            }
            it.showError?.let { err ->
                toast(err)
            }
        })
    }
}