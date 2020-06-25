package pers.zander.kotlinmvvm.practice.model.api

import pers.zander.kotlinmvvm.practice.model.bean.Navigation
import pers.zander.kotlinmvvm.practice.model.bean.User
import pers.zander.kotlinmvvm.practice.model.bean.WanResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
interface WanService {
    companion object{
        const val BASE_URL = "https://www.wanandroid.com"
    }

    @GET("/navi/json")
    suspend fun getNavigation(): WanResponse<List<Navigation>>

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") userName:String, @Field("password") password:String): WanResponse<User>

    @GET("/user/logout/json")
    suspend fun logOut(): WanResponse<Any>

    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(@Field("username") userName: String, @Field("password") passWord: String, @Field("repassword") rePassWord: String): WanResponse<User>
}