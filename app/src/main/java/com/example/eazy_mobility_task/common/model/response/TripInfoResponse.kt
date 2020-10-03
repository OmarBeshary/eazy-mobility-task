package com.example.eazy_mobility_task.common.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TripInfoResponse(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "pickup_station")
    val pickUpStation: String,
    @field:Json(name = "drop_off_station")
    val dropOffStation: String,
    @field:Json(name = "pickup_lat")
    val pickupLat: Double,
    @field:Json(name = "pickup_lng")
    val pickupLng: Double,
    @field:Json(name = "drop_off_lat")
    val dropOffLat: Double,
    @field:Json(name = "drop_off_lng")
    val dropOffLng: Double
)