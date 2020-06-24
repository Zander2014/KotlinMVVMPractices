package pers.zander.kotlinmvvm.practice

import pers.zander.mvvmcore.Result

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */

//扩展Result，提供checkResult，checkSuccess方法
inline fun <T : Any> Result<T>.checkResult(crossinline onSuccess: (T)->Unit, crossinline onError: (String?)->Unit){
    if(this is Result.Success){
        onSuccess(data)
    }else if(this is Result.Error){
        onError(exception.message)
    }
}

inline fun <T : Any> Result<T>.checkSuccess(success : (T) -> Unit){
    if(this is Result.Success){
        success(data)
    }
}