package pers.zander.kotlinmvvm.practice.model.repository

import pers.zander.kotlinmvvm.practice.model.api.BaseRepository
import pers.zander.kotlinmvvm.practice.model.api.WanRetrofitClient
import pers.zander.kotlinmvvm.practice.model.bean.ArticleList
import pers.zander.kotlinmvvm.practice.model.bean.SystemParent
import pers.zander.mvvmcore.Result

/**
 * Created by Zander on 2020/6/27.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class SystemRepository: BaseRepository() {
    suspend fun getSystemTypeDetail(cid: Int, page: Int): Result<ArticleList> {
        return safeApiCall(call = {requestSystemTypeDetail(cid, page)},errorMessage = "网络错误")
    }

    suspend fun getSystemTypes(): Result<List<SystemParent>> {
        return safeApiCall(call = { requestSystemTypes() }, errorMessage = "网络错误")
    }

    suspend fun getBlogArticle(cid: Int, page: Int): Result<ArticleList> {
        return safeApiCall(call = {requestBlogArticle(cid, page)},errorMessage = "网络错误")
    }

    private suspend fun requestSystemTypes(): Result<List<SystemParent>> =
        executeResponse(WanRetrofitClient.service.getSystemType())

    private suspend fun requestSystemTypeDetail(cid: Int,page: Int): Result<ArticleList> =
        executeResponse(WanRetrofitClient.service.getSystemTypeDetail(page, cid) )

    private suspend fun requestBlogArticle(cid: Int,page: Int): Result<ArticleList> =
        executeResponse(WanRetrofitClient.service.getSystemTypeDetail(page, cid) )
}