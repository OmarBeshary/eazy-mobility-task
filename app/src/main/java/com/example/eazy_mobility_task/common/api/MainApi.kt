package com.example.eazy_mobility_task.common.api

import com.example.eazy_mobility_task.common.model.response.MainResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MainApi {
    @GET("facts/random")
    fun getFacts () : Single<MainResponse>
}