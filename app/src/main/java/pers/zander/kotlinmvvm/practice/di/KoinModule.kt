package pers.zander.kotlinmvvm.practice.di

import org.koin.dsl.module

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */

val viewModelModule = module {

}

val repositoryModule = module {

}

val appModule = listOf(viewModelModule, repositoryModule)