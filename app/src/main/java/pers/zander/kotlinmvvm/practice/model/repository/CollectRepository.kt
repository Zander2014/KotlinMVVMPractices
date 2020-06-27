package pers.zander.kotlinmvvm.practice.model.repository

import pers.zander.kotlinmvvm.practice.model.api.BaseRepository
import pers.zander.kotlinmvvm.practice.model.api.WanRetrofitClient
import pers.zander.kotlinmvvm.practice.model.bean.ArticleList
import pers.zander.mvvmcore.Result

/**
 * Created by Zander on 2020/6/27.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class CollectRepository: BaseRepository() {
    suspend fun getCollectArticles(page: Int): Result<ArticleList> {
        return safeApiCall(call = { requestCollectArticles(page) }, errorMessage = "网络错误")
    }

    suspend fun collectArticle(articleId: Int): Result<ArticleList> {
        return safeApiCall(call = { requestCollectArticle(articleId) }, errorMessage = "网络错误")
    }

    suspend fun unCollectArticle(articleId: Int): Result<ArticleList> {
        return safeApiCall(call = { requestCancelCollectArticle(articleId) }, errorMessage = "网络错误")
    }

    private suspend fun requestCollectArticles(page: Int): Result<ArticleList> =
        executeResponse(WanRetrofitClient.service.getCollectArticles(page))

    private suspend fun requestCollectArticle(articleId: Int): Result<ArticleList> =
        executeResponse(WanRetrofitClient.service.collectArticle(articleId))

    private suspend fun requestCancelCollectArticle(articleId: Int): Result<ArticleList> =
        executeResponse(WanRetrofitClient.service.cancelCollectArticle(articleId))
}