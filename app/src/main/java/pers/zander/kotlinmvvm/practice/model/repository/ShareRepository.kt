package pers.zander.kotlinmvvm.practice.model.repository

import pers.zander.kotlinmvvm.practice.model.api.BaseRepository
import pers.zander.kotlinmvvm.practice.model.api.WanRetrofitClient
import pers.zander.mvvmcore.Result

/**
 * Created by Zander on 2020/6/25.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class ShareRepository: BaseRepository() {

    suspend fun shareArticle(title: String, url: String): Result<String>{
        return safeApiCall({requestShareArticle(title, url)}, "分享失败")
    }

    private suspend fun requestShareArticle(title: String, url: String): Result<String>{
        return executeResponse(WanRetrofitClient.service.shareArticle(title, url))
    }
}