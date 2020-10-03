package com.example.eazy_mobility_task.common.api

import com.example.eazy_mobility_task.common.model.response.TripInfoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TripsApi {
    @GET("trip/latest")
    fun getLatestTrip(): Single<TripInfoResponse>

    @GET("trip/{id}")
    fun getTripInfo(@Path("id") tripId: Long): Single<TripInfoResponse>

}