package com.perry.wanandroid.kt.model.romote

import com.perry.wanandroid.kt.model.bean.*
import retrofit2.http.*

interface WanAndroidApi {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com/"
    }

    @GET("/article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int): Response<ArticleList>

    @GET("/banner/json")
    suspend fun getBanner(): Response<List<Banner>>

    @GET("/tree/json")
    suspend fun getSystemType(): Response<List<SystemParent>>

    @GET("/article/list/{page}/json")
    suspend fun getSystemTypeDetail(@Path("page") page: Int, @Query("cid") cid: Int): Response<ArticleList>

    @GET("/navi/json")
    suspend fun getNavigation(): Response<List<Navigation>>

    @GET("/project/tree/json")
    suspend fun getProjectType(): Response<List<SystemParent>>

    @GET("/wxarticle/chapters/json")
    suspend fun getBlogType(): Response<List<SystemParent>>

    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun getBlogArticle(@Path("id") id: Int, @Path("page") page: Int): Response<ArticleList>

    @GET("/project/list/{page}/json")
    suspend fun getProjectTypeDetail(@Path("page") page: Int, @Query("cid") cid: Int): Response<ArticleList>

    @GET("/article/listproject/{page}/json")
    suspend fun getLastedProject(@Path("page") page: Int): Response<ArticleList>

    @GET("/friend/json")
    suspend fun getWebsites(): Response<List<Hot>>

    @GET("/hotkey/json")
    suspend fun getHot(): Response<List<Hot>>

    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    suspend fun searchHot(@Path("page") page: Int, @Field("k") key: String): Response<ArticleList>

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): Response<User>

    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(@Field("username") userName: String, @Field("password") passWord: String, @Field("repassword") rePassWord: String): Response<User>

    @GET("/user/logout/json")
    suspend fun logout() : Response<Any>

    @GET("/lg/collect/list/{page}/json")
    suspend fun getCollectArticles(@Path("page") page: Int): Response<ArticleList>

    @POST("/lg/collect/{id}/json")
    suspend fun collectArticle(@Path("id") id: Int): Response<ArticleList>

    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun cancelCollectArticle(@Path("id") id: Int): Response<ArticleList>

}