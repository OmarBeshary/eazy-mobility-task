package com.example.eazy_mobility_task.common.network

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

abstract class RetrofitClient : IRetrofitClient {
    protected open lateinit var retrofit: Retrofit

    fun buildRetrofit(): RetrofitClient {
        if (!this::retrofit.isInitialized)
            retrofit = this.getRetrofitBuilder().build()
        return this
    }

    protected open fun getOkHttpBuilder() : OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(getHttpPrettyPrintLoggingInterceptor())
        return builder
    }

    protected open fun getRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(getBaseURL())
            .addConverterFactory(getConverterFactory())
            .client(getOkHttpBuilder().build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    override fun <APIsInterface> getRetrofitClient(restAPIsInterface: Class<APIsInterface>): APIsInterface {
        return retrofit.create(restAPIsInterface)
    }

    protected abstract fun getBaseURL(): String

    protected abstract fun getConverterFactory(): Converter.Factory

    private fun getHttpPrettyPrintLoggingInterceptor(): LoggingInterceptor {
        return LoggingInterceptor.Builder()
            .setLevel(Level.BODY)
            .log(Platform.INFO)
            .build()
    }

}