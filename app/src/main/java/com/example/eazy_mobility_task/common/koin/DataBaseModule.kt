package com.example.eazy_mobility_task.common.koin

import androidx.room.Room
import com.example.eazy_mobility_task.common.db.ProjectDb
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModules = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ProjectDb::class.java,
            ProjectDb::class.java.simpleName
        ).fallbackToDestructiveMigration()
            .build()
    }
    single { get<ProjectDb>().tripsDao() }
}