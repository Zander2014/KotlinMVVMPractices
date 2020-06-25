package pers.zander.kotlinmvvm.practice.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import pers.zander.kotlinmvvm.practice.model.api.WanRetrofitClient
import pers.zander.kotlinmvvm.practice.model.api.WanService
import javax.inject.Singleton

/**
 * Created by Zander on 2020/6/25.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
@InstallIn(ApplicationComponent::class)
@Module
object HiltModule {

    @Provides
    @Singleton
    fun providerService() : WanService{
        return WanRetrofitClient.getService(WanService::class.java, WanService.BASE_URL)
    }
}