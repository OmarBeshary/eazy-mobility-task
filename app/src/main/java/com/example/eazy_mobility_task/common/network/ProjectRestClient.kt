package com.example.eazy_mobility_task.common.network

import org.koin.core.KoinComponent
import retrofit2.converter.moshi.MoshiConverterFactory

class ProjectRestClient : BaseRestClient(), KoinComponent {

    override fun getBaseURL(): String = "https://cat-fact.herokuapp.com/"

    override fun getConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()


}