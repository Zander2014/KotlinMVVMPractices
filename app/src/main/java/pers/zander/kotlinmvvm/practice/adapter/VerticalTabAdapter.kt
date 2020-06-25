package pers.zander.kotlinmvvm.practice.adapter

import q.rorbin.verticaltablayout.adapter.TabAdapter
import q.rorbin.verticaltablayout.widget.ITabView

/**
 * Created by Zander on 2020/6/25.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class VerticalTabAdapter(private val titles: List<String>) : TabAdapter {
    override fun getIcon(position: Int) = null

    override fun getBadge(position: Int) = null

    override fun getBackground(position: Int): Int = -1

    override fun getTitle(position: Int): ITabView.TabTitle {
        return ITabView.TabTitle.Builder()
            .setContent(titles[position])
            .setTextColor(-0xc94365, -0x8a8a8b)
            .build()
    }

    override fun getCount(): Int = titles.size
}