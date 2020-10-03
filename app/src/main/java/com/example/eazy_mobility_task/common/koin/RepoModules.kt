package com.example.eazy_mobility_task.common.koin

import com.example.eazy_mobility_task.common.repo.TripsRepo
import org.koin.dsl.module

val repoModules = module {
    factory { TripsRepo(infoApi = get()) }
}