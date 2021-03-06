package pers.zander.kotlinmvvm.practice.adapter

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import pers.zander.kotlinmvvm.practice.R
import pers.zander.kotlinmvvm.practice.model.bean.Article
import pers.zander.kotlinmvvm.practice.model.bean.Navigation
import pers.zander.kotlinmvvm.practice.ui.BrowserActivity

/**
 * Created by Zander on 2020/6/25.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class NavigationAdapter(layoutResId: Int = R.layout.item_navigation):BaseQuickAdapter<Navigation, BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder, item: Navigation) {
        helper.setText(R.id.navigationName, item.name)
        helper.getView<TagFlowLayout>(R.id.navigationTagLayout).run{
            adapter = object : TagAdapter<Article>(item.articles){
                override fun getCount(): Int {
                    return item.articles.size
                }
                override fun getView(parent: FlowLayout, position: Int, t: Article): View {
                    val tv = LayoutInflater.from(parent.context).inflate(R.layout.tag, parent, false) as TextView
                    tv.text = t.title
                    return tv
                }
            }
            setOnTagClickListener { view, position, _-> findNavController(view).navigate(R.id.action_tab_to_browser, bundleOf(
                BrowserActivity.URL to item.articles[position].link))
                true
            }
        }
    }
}