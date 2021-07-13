package com.apps.service.repository


import com.apps.service.repository.gifRepository.IGifRepository
import com.apps.service.repository.gifRepository.GifRepository
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Created by Moayad Albarbary on 7/13/2021.
 * module to prepare Repository objects to inject it
 * @factory new instance in every call
 * @single single instance in the whole app
 */
val RepositoryModule = module {
    single { GifRepository() } bind IGifRepository::class
}