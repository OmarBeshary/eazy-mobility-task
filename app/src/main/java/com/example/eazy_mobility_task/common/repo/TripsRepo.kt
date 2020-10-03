package com.example.eazy_mobility_task.common.repo

import com.example.eazy_mobility_task.common.api.TripsApi
import com.example.eazy_mobility_task.common.db.dao.TripDao
import com.example.eazy_mobility_task.common.db.util.SimpleCacheHandler
import com.example.eazy_mobility_task.common.model.response.TripInfoResponse
import io.reactivex.Completable
import io.reactivex.Single

const val FIRST_TRIP_ID = 1L
const val LATEST_TRIP_ID = 5L

class TripsRepo(private val infoApi: TripsApi, private val tripDao: TripDao) {


    fun getLatestTrip(): Single<TripInfoResponse> =
        tripDao.exists(LATEST_TRIP_ID).flatMap { exists ->
            if (exists)
                return@flatMap tripDao.getTripById(LATEST_TRIP_ID)
            else
                return@flatMap saveAndGetLatestTrip()
        }


    fun getTripInfo(id: Long): Single<TripInfoResponse> =
        tripDao.exists(id).flatMap { exists ->
            if (exists)
                return@flatMap tripDao.getTripById(id)
            else
                return@flatMap saveAndGetTrip(id)

        }

    private fun saveAndGetLatestTrip() = object : SimpleCacheHandler<TripInfoResponse>() {
        override fun saveToDB(data: TripInfoResponse): Completable =
            tripDao.saveTrip(trip = data)

        override fun fetchFromNetwork(): Single<TripInfoResponse> = infoApi.getLatestTrip()

        override fun fetchFromDB(): Single<TripInfoResponse> = tripDao.getTripById(LATEST_TRIP_ID)

    }.cacheAndRetrieve()

    private fun saveAndGetTrip(id: Long) = object : SimpleCacheHandler<TripInfoResponse>() {
        override fun saveToDB(data: TripInfoResponse): Completable =
            tripDao.saveTrip(trip = data)

        override fun fetchFromNetwork(): Single<TripInfoResponse> = infoApi.getTripInfo(id)

        override fun fetchFromDB(): Single<TripInfoResponse> = tripDao.getTripById(id)

    }.cacheAndRetrieve()


}