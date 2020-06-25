package pers.zander.kotlinmvvm.practice.model.repository

import com.google.gson.Gson
import pers.zander.kotlinmvvm.practice.App
import pers.zander.kotlinmvvm.practice.model.api.BaseRepository
import pers.zander.kotlinmvvm.practice.model.api.WanService
import pers.zander.kotlinmvvm.practice.model.bean.User
import pers.zander.kotlinmvvm.practice.util.Preference
import pers.zander.mvvmcore.Result
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
@Singleton
class LoginRepository @Inject constructor(val service: WanService): BaseRepository(){
    private var isLogin by Preference(Preference.IS_LOGIN, false)
    private var userJson by Preference(Preference.USER_GSON, "")

    suspend fun login(username: String, password: String): Result<User>{
        return safeApiCall(call = {requestLogin(username, password)}, errorMessage = "登录失败")
    }

    private suspend fun requestLogin(username: String, password: String): Result<User>{
        val response = service.login(username, password)
        return executeResponse(response, {
            val user = response.data
            isLogin = true
            userJson = Gson().toJson(user)
            App.CURRENT_USER = user
        })
    }

    suspend fun register(username: String, password: String): Result<User>{
        return safeApiCall({requestRegister(username, password)}, "注册失败")
    }

    private suspend fun requestRegister(username: String, password: String): Result<User>{
        val response = service.register(username, password, password)
        return executeResponse(response, {requestLogin(username, password)})
    }
}