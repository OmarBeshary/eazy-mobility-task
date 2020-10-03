package com.example.eazy_mobility_task.common.services.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulersServiceImp : SchedulersService {
    override fun getIOThread(): Scheduler = Schedulers.io()

    override fun getAndroidMainThread(): Scheduler = AndroidSchedulers.mainThread()

    override fun getComputationThread(): Scheduler = Schedulers.computation()
}