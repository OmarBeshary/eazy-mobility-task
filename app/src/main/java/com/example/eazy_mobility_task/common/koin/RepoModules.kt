package com.example.eazy_mobility_task.common.koin

import com.example.eazy_mobility_task.common.repo.MainRepo
import org.koin.dsl.module

val repoModules = module {
    factory { MainRepo(api = get()) }
}