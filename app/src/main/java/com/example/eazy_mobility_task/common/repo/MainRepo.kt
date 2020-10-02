package com.example.eazy_mobility_task.common.repo

import com.example.eazy_mobility_task.common.api.MainApi
import com.example.eazy_mobility_task.common.model.response.MainResponse
import io.reactivex.Single

class MainRepo(private val api: MainApi) {

    fun getFacts () : Single<MainResponse> = api.getFacts()
}