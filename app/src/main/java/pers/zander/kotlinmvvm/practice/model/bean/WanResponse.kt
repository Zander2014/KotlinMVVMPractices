package pers.zander.kotlinmvvm.practice.model.bean

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
data class WanResponse<out T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)