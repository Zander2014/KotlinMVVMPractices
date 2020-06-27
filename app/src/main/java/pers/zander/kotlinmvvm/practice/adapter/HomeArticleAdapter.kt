package pers.zander.kotlinmvvm.practice.adapter

import pers.zander.kotlinmvvm.practice.APP_START
import pers.zander.kotlinmvvm.practice.BR
import pers.zander.kotlinmvvm.practice.R
import pers.zander.kotlinmvvm.practice.model.bean.Article
import pers.zander.mvvmcore.util.Timer

/**
 * Created by Zander on 2020/6/27.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class HomeArticleAdapter(layoutResId: Int = R.layout.item_article): BaseBindAdapter<Article>(layoutResId, BR.article) {
    private var showStar = true

    fun showStar(show: Boolean){
        showStar = show
    }

    override fun convert(helper: BindViewHolder, item: Article) {
        super.convert(helper, item)
        helper.addOnClickListener(R.id.articleStar)
        if (showStar) helper.setImageResource(R.id.articleStar, if (item.collect) R.drawable.timeline_like_pressed else R.drawable.timeline_like_normal)
        else helper.setVisible(R.id.articleStar, false)
        helper.setText(R.id.articleAuthor, if (item.author.isBlank()) "分享者: ${item.shareUser}" else item.author)
        Timer.stop(APP_START)
    }
}