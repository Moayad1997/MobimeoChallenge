package com.apps.mobimeochallenge.di


import com.apps.mobimeochallenge.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Moayad Albarbary on 7/13/2021.
 * module to prepare ViewModels objects to inject it
 * @viewModel to inject class as viewModel
 */
val ViewModelsModule = module {
    listOf(viewModel { SearchViewModel(get()) })
}