package com.example.eazy_mobility_task.common.koin

import com.example.eazy_mobility_task.common.services.SchedulersService
import com.example.eazy_mobility_task.common.services.SchedulersServiceImp
import org.koin.dsl.module

val schedulerModule = module {
    single { SchedulersServiceImp() as SchedulersService }
}