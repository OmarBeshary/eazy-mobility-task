package com.example.eazy_mobility_task.common.network

interface IRetrofitClient {
    fun <APIsInterface> getRetrofitClient(restAPIsInterface: Class<APIsInterface>): APIsInterface
}