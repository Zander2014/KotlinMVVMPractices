package pers.zander.kotlinmvvm.practice.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import pers.zander.kotlinmvvm.practice.R

/**
 * Created by Zander on 2020/6/27.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
open class BaseBindAdapter<T>(layoutResId: Int, br: Int): BaseQuickAdapter<T, BaseBindAdapter.BindViewHolder>(layoutResId) {

    private val _br = br

    override fun convert(helper: BindViewHolder, item: T) {
        helper.binding.run {
            setVariable(_br, item)
            executePendingBindings()
        }
    }

    override fun getItemView(layoutResId: Int, parent: ViewGroup?): View {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(mLayoutInflater, layoutResId, parent, false)
            ?: return super.getItemView(layoutResId, parent)
        return binding.root.apply {
            setTag(R.id.BaseQuickAdapter_databinding_support, binding)
        }
    }

    class BindViewHolder(view: View): BaseViewHolder(view){
        val binding: ViewDataBinding
            get() = itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ViewDataBinding
    }

}