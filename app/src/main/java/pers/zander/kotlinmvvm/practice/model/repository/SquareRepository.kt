package pers.zander.kotlinmvvm.practice.model.repository

import pers.zander.kotlinmvvm.practice.model.api.BaseRepository
import pers.zander.kotlinmvvm.practice.model.api.WanRetrofitClient
import pers.zander.kotlinmvvm.practice.model.bean.ArticleList
import pers.zander.kotlinmvvm.practice.util.isSuccess
import pers.zander.mvvmcore.Result
import java.io.IOException

/**
 * Created by Zander on 2020/6/26.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class SquareRepository: BaseRepository() {
    suspend fun getSquareArticleList(page: Int): pers.zander.mvvmcore.Result<ArticleList>{
        return safeApiCall({requestSquareArticleList(page)}, "网络异常")
    }

    private suspend fun requestSquareArticleList(page: Int): Result<ArticleList>{
        val response = WanRetrofitClient.service.getSquareArticleList(page)
        return if(response.isSuccess()) Result.Success(response.data)
        else Result.Error(IOException(response.errorMsg))
    }
}