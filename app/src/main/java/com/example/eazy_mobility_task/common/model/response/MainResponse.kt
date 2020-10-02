package com.example.eazy_mobility_task.common.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MainResponse(val type:String)