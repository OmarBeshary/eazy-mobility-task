package com.example.eazy_mobility_task.common.repo

import com.example.eazy_mobility_task.common.api.TripsApi
import com.example.eazy_mobility_task.common.model.response.TripInfoResponse
import io.reactivex.Single

class TripsRepo(private val infoApi: TripsApi) {

    fun getLatestTrip(): Single<TripInfoResponse> = infoApi.getLatestTrip()

    fun getTripInfo(id: Long): Single<TripInfoResponse> = infoApi.getTripInfo(id)
}