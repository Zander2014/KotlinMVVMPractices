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
class ProjectRepository: BaseRepository() {
    suspend fun getProjectTypeDetailList(page: Int, cid: Int): Result<ArticleList> {
        return safeApiCall(call = {requestProjectTypeDetailList(page, cid)},errorMessage = "发生未知错误")
    }

    suspend fun getLastedProject(page: Int): Result<ArticleList> {
        return safeApiCall(call = {requestLastedProject(page)},errorMessage = "发生未知错误")
    }

    suspend fun getProjectTypeList(): Result<List<SystemParent>> {
        return safeApiCall(call = {requestProjectTypeList()},errorMessage = "网络错误")
    }

    suspend fun getBlog(): Result<List<SystemParent>> {
        return safeApiCall(call = {requestBlogTypeList()},errorMessage = "网络错误")
    }

    private suspend fun requestProjectTypeDetailList(page: Int, cid: Int) =
        executeResponse(WanRetrofitClient.service.getProjectTypeDetail(page, cid))

    private suspend fun requestLastedProject(page: Int): Result<ArticleList> =
        executeResponse(WanRetrofitClient.service.getLastedProject(page))

    private suspend fun requestProjectTypeList() =
        executeResponse(WanRetrofitClient.service.getProjectType())

    private suspend fun requestBlogTypeList() =
        executeResponse(WanRetrofitClient.service.getBlogType())
}