package com.example.eazy_mobility_task.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eazy_mobility_task.common.db.dao.TripDao
import com.example.eazy_mobility_task.common.model.response.TripInfoResponse

@Database(entities = [TripInfoResponse::class], version = 1)
abstract class ProjectDb : RoomDatabase() {
    abstract fun tripsDao(): TripDao
}