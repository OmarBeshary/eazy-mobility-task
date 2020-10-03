package com.example.eazy_mobility_task.common.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eazy_mobility_task.common.model.response.TripInfoResponse
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TripDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTrip(trip: TripInfoResponse): Completable

    @Query("select * from trips where id = :tripId")
    fun getTripById(tripId: Long): Single<TripInfoResponse>

    @Query("select exists (select 1 from trips where id = :id)")
    fun exists(id: Long): Single<Boolean>
}