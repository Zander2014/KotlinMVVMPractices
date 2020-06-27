package pers.zander.kotlinmvvm.practice.model.api

import pers.zander.kotlinmvvm.practice.model.bean.*
import retrofit2.http.*

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

    @GET("/article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int): WanResponse<ArticleList>

    @GET("/user_article/list/{page}/json")
    suspend fun getSquareArticleList(@Path("page") page: Int): WanResponse<ArticleList>

    @GET("/banner/json")
    suspend fun getBanner(): WanResponse<List<Banner>>

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

    @GET("/project/list/{page}/json")
    suspend fun getProjectTypeDetail(@Path("page") page: Int, @Query("cid") cid: Int): WanResponse<ArticleList>

    @GET("/article/listproject/{page}/json")
    suspend fun getLastedProject(@Path("page") page: Int): WanResponse<ArticleList>

    @GET("/project/tree/json")
    suspend fun getProjectType(): WanResponse<List<SystemParent>>

    @GET("/wxarticle/chapters/json")
    suspend fun getBlogType(): WanResponse<List<SystemParent>>

    @GET("/lg/collect/list/{page}/json")
    suspend fun getCollectArticles(@Path("page") page: Int): WanResponse<ArticleList>

    @POST("/lg/collect/{id}/json")
    suspend fun collectArticle(@Path("id") id: Int): WanResponse<ArticleList>

    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun cancelCollectArticle(@Path("id") id: Int): WanResponse<ArticleList>

    @FormUrlEncoded
    @POST("/lg/user_article/add/json")
    suspend fun shareArticle(@Field("title") title: String, @Field("link") url: String): WanResponse<String>

    @GET("/tree/json")
    suspend fun getSystemType(): WanResponse<List<SystemParent>>

    @GET("/article/list/{page}/json")
    suspend fun getSystemTypeDetail(@Path("page") page: Int, @Query("cid") cid: Int): WanResponse<ArticleList>
}