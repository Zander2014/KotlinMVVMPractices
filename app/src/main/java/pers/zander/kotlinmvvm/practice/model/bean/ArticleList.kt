package pers.zander.kotlinmvvm.practice.model.bean

import java.io.Serializable

/**
 * Created by Zander on 2020/6/26.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
data class ArticleList(val offset: Int,
                       val size: Int,
                       val total: Int,
                       val pageCount: Int,
                       val curPage: Int,
                       val over: Boolean,
                       val datas: List<Article>):Serializable