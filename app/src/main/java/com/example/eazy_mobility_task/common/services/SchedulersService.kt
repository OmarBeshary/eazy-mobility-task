package com.example.eazy_mobility_task.common.services

import io.reactivex.Scheduler

/**
 * This wrapper above [io.reactivex.schedulers.Schedulers] class that could be injected
 */

interface SchedulersService {
    fun getIOThread(): Scheduler
    fun getAndroidMainThread(): Scheduler
    fun getComputationThread(): Scheduler
}