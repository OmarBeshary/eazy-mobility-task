package com.example.eazy_mobility_task.common.koin

import com.example.eazy_mobility_task.common.api.MainApi
import com.example.eazy_mobility_task.common.network.IRetrofitClient
import com.example.eazy_mobility_task.common.network.ProjectRestClient
import org.koin.dsl.module

val restClientModule = module {
    single { ProjectRestClient() as IRetrofitClient }
}

val restAPIsModules = module {
    factory { get<IRetrofitClient>().getRetrofitClient(MainApi::class.java) }
}