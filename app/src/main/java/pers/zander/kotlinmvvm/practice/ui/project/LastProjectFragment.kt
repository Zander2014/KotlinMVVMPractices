package pers.zander.kotlinmvvm.practice.ui.project

import android.os.Bundle
import pers.zander.kotlinmvvm.practice.R
import pers.zander.mvvmcore.base.BaseFragment

/**
 * Created by Zander on 2020/6/26.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class LastProjectFragment: BaseFragment() {
    private val cid by lazy { arguments?.get(CID) }
    private val lasted by lazy { arguments?.get(LASTED) }// 区分是最新项目 还是项目分类
    companion object {
        private const val CID = "cid"
        private const val LASTED = "lasted"
        fun newInstance(cid:Int, lasted: Boolean): LastProjectFragment{
            val fragment = LastProjectFragment()
            val bundle = Bundle()
            bundle.putInt(CID, cid)
            bundle.putBoolean(LASTED, lasted)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_empty

    override fun initView() {

    }

    override fun initData() {

    }
}