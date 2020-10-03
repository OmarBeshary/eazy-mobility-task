package com.example.eazy_mobility_task.common

import android.app.Application
import com.example.eazy_mobility_task.common.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        injectKoin()
    }

    private fun injectKoin() {
        startKoin {
            androidContext(this@App)
            modules(getKoinModules())
        }
    }

    private fun getKoinModules(): List<Module> = listOf(
        viewModelModules, restClientModule,
        restAPIsModules, repoModules,
        schedulerModule
    )
}
