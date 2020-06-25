package pers.zander.kotlinmvvm.practice.ui.login

import android.app.ProgressDialog
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.title_layout.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import pers.zander.kotlinmvvm.practice.R
import pers.zander.kotlinmvvm.practice.databinding.ActivityLoginBinding
import pers.zander.mvvmcore.base.BaseVMFragment
import pers.zander.utils.ktx.ext.toast

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class LoginFragment : BaseVMFragment<LoginViewModel>() {
    override fun getLayoutResId(): Int = R.layout.activity_login

    override fun initVM(): LoginViewModel = getViewModel()

    override fun initView() {
        (mBinding as ActivityLoginBinding).viewModel = mViewModel
        mToolbar.setTitle("登录")
        mToolbar.setNavigationIcon(R.drawable.arrow_back)
    }

    override fun initData() {
        mToolbar.setNavigationOnClickListener{findNavController().navigateUp()}
    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(viewLifecycleOwner, Observer{
                if(it.isLoading) showProgressDialog()

                it.isSuccess?.let{
                    dismissProgressDialog()
                    findNavController().navigateUp()
                }

                it.isError?.let{
                    dismissProgressDialog()
                    activity?.toast(it)
                }
            })
        }
    }

    private var progressDialog: ProgressDialog? = null
    private fun showProgressDialog() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(context)
        progressDialog?.show()
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }
}