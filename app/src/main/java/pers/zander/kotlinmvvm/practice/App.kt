package pers.zander.kotlinmvvm.practice

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pers.zander.kotlinmvvm.practice.di.appModule
import pers.zander.mvvmcore.util.Timer
import kotlin.properties.Delegates

/**
 * Created by Zander on 2020/6/24.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
@HiltAndroidApp
class App : Application() {
    companion object{
        var CONTEXT : Context by Delegates.notNull()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Timer.start(APP_START)
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}