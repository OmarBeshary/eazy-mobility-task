package com.example.eazy_mobility_task.common.network

import retrofit2.converter.moshi.MoshiConverterFactory

class ProjectRestClient : BaseRestClient(){

    override fun getBaseURL(): String = "https://eazy-mobility-task.getsandbox.com/"

    override fun getConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()


}