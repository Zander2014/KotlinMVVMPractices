package pers.zander.kotlinmvvm.practice.di

import org.koin.dsl.module
import pers.zander.kotlinmvvm.practice.model.api.WanRetrofitClient
import pers.zander.kotlinmvvm.practice.model.api.WanService

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */

val viewModelModule = module {

}

val repositoryModule = module {
//    single { WanRetrofitClient.getService(WanService::class.java, WanService.BASE_URL) }
}

val appModule = listOf(viewModelModule, repositoryModule)