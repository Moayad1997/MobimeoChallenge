package com.apps.mobimeochallenge

import android.app.Application
import com.apps.mobimeochallenge.di.ViewModelsModule
import com.apps.service.repository.RepositoryModule
import com.apps.service.retrofit.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Moayad Albarbary on 7/13/2021.
 */
class MobimeoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    /**
     * init DI predefine modules
     * @context application context to Koin container
     * */
    private fun initKoin() {
        startKoin {
            androidContext(this@MobimeoApplication)
            modules(
                listOf(
                    NetworkModule,
                    RepositoryModule,
                    ViewModelsModule
                )
            )
        }
    }
}