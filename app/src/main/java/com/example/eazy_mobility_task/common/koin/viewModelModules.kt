package com.example.eazy_mobility_task.common.koin

import com.example.eazy_mobility_task.features.TripsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { TripsViewModel(repo = get()) }
}