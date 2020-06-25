package pers.zander.kotlinmvvm.practice.ui.login

import android.app.ProgressDialog
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.getViewModel
import pers.zander.kotlinmvvm.practice.BR
import pers.zander.kotlinmvvm.practice.R
import pers.zander.kotlinmvvm.practice.model.bean.Title
import pers.zander.mvvmcore.base.BaseVMActivity
import pers.zander.utils.ktx.ext.toast

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
@AndroidEntryPoint
class LoginActivity : BaseVMActivity<LoginViewModel>() {
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun getLayoutResId(): Int = R.layout.activity_login
    override fun initVM(): LoginViewModel = getViewModel()

    override fun initView() {
        mBinding.run {
            setVariable(BR.viewModel, loginViewModel)
            setVariable(BR.title, Title(R.string.login, R.drawable.arrow_back) { onBackPressed() })
        }
    }

    override fun initData() {
    }

    override fun startObserve() {
        loginViewModel.apply {
            uiState.observe(this@LoginActivity, Observer {
                if(it.isLoading) showProgressDialog()

                it.isSuccess?.let {
                    dismissProgressDialog()
                    finish()
                }

                it.isError?.let{err->
                    dismissProgressDialog()
                    toast(err)
                }
            })
        }
    }

    private var progressDialog: ProgressDialog? = null
    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }

    private fun showProgressDialog() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(this)
        progressDialog?.show()
    }
}