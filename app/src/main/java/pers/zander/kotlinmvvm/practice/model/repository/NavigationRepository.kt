package pers.zander.kotlinmvvm.practice.model.repository

import pers.zander.kotlinmvvm.practice.model.api.BaseRepository
import pers.zander.kotlinmvvm.practice.model.api.WanRetrofitClient
import pers.zander.kotlinmvvm.practice.model.bean.Navigation
import pers.zander.mvvmcore.Result

/**
 * Created by Zander on 2020/6/25.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
class NavigationRepository : BaseRepository(){
    suspend fun getNavigation(): Result<List<Navigation>>{
        return safeApiCall({requestNavigation()}, "获取数据失败")
    }

    private suspend fun requestNavigation(): Result<List<Navigation>> =
        executeResponse(WanRetrofitClient.service.getNavigation())
}