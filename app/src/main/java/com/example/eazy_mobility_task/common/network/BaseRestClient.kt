package com.example.eazy_mobility_task.common.network

abstract class BaseRestClient : RetrofitClient() {
    init {
        buildRetrofit()
    }
}