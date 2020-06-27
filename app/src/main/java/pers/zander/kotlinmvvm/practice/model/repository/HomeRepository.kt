package pers.zander.kotlinmvvm.practice.model.repository

import pers.zander.kotlinmvvm.practice.model.api.BaseRepository
import pers.zander.kotlinmvvm.practice.model.api.WanRetrofitClient
import pers.zander.kotlinmvvm.practice.model.bean.ArticleList
import pers.zander.kotlinmvvm.practice.model.bean.Banner
import pers.zander.mvvmcore.Result

/**
 * Created by Zander on 2020/6/26.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class HomeRepository: BaseRepository() {
    suspend fun getBanners():Result<List<Banner>>{
        return safeApiCall({requestBanners()}, "")
    }

    private suspend fun requestBanners():Result<List<Banner>>{
        return executeResponse(WanRetrofitClient.service.getBanner())
    }

    suspend fun getArticleList(page: Int):Result<ArticleList>{
        return safeApiCall({requestArticleList(page)}, "")
    }

    private suspend fun requestArticleList(page: Int):Result<ArticleList>{
        return executeResponse(WanRetrofitClient.service.getHomeArticles(page))
    }
}