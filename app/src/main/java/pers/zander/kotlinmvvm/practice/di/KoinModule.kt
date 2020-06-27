package pers.zander.kotlinmvvm.practice.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pers.zander.kotlinmvvm.practice.CoroutinesDispatcherProvider
import pers.zander.kotlinmvvm.practice.model.api.WanRetrofitClient
import pers.zander.kotlinmvvm.practice.model.api.WanService
import pers.zander.kotlinmvvm.practice.model.repository.*
import pers.zander.kotlinmvvm.practice.ui.login.LoginViewModel
import pers.zander.kotlinmvvm.practice.ui.navigation.NavigationViewModel
import pers.zander.kotlinmvvm.practice.ui.share.ShareViewModel
import pers.zander.kotlinmvvm.practice.ui.square.ArticleViewModel

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { NavigationViewModel(get()) }
    viewModel { ShareViewModel(get()) }
    viewModel { ArticleViewModel(get(), get(), get(), get(), get()) }
}

val repositoryModule = module {
    single { WanRetrofitClient.getService(WanService::class.java, WanService.BASE_URL) }
    single { CoroutinesDispatcherProvider() }
    single { LoginRepository(get()) }
    single { NavigationRepository() }
    single { ShareRepository() }
    single { HomeRepository() }
    single { SquareRepository() }
    single { ProjectRepository() }
    single { CollectRepository() }
    single { SystemRepository() }
}

val appModule = listOf(viewModelModule, repositoryModule)