package com.example.eazy_mobility_task.common.db.util

import io.reactivex.Completable
import io.reactivex.Single


abstract class SimpleCacheHandler<T> {

    abstract fun saveToDB(data: T): Completable

    abstract fun fetchFromNetwork(): Single<T>

    abstract fun fetchFromDB(): Single<T>

    fun cacheAndRetrieve(): Single<T> {
        return fetchFromNetwork()
            .flatMapCompletable { response ->
                saveToDB(response)
            }.andThen(fetchFromDB())
    }
}
