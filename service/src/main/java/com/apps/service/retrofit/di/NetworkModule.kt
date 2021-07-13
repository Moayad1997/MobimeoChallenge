package com.apps.service.retrofit.di

import com.apps.service.retrofit.config.AuthInterceptor
import com.apps.service.retrofit.utils.*
import org.koin.dsl.module

/**
 * Created by Moayad Albarbary on 7/13/2021.
 * module to prepare network objects to inject it
 * @factory new instance in every call
 * @single single instance in the whole app
 */
val NetworkModule = module {
    factory { AuthInterceptor() }
    factory {
        provideOkHttpClient(
            get(),
            get()
        )
    }
    factory { provideLoggingInterceptor() }
    single { provideRetrofit(get()) }
    factory { provideAppAPI(get()) }
    factory { ResponseHandler() }
}