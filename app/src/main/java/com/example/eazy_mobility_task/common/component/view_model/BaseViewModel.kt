package com.example.eazy_mobility_task.common.component.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eazy_mobility_task.common.component.states.IViewState
import com.example.eazy_mobility_task.common.component.states.Result
import com.example.eazy_mobility_task.common.services.SchedulersService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseViewModel : ViewModel(), KoinComponent {

    private val schedulersService: SchedulersService by inject()
    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun <T> Single<T>.execute(state: MutableLiveData<IViewState<T>>) {
        state.value = Result.loading()
        val singleSubscription = this
            .subscribeOn(schedulersService.getIOThread())
            .observeOn(schedulersService.getAndroidMainThread())
            .subscribe({
                state.value = Result.success(it)
            }, {
                state.value = Result.error(it.localizedMessage)
            })
        compositeDisposable.add(singleSubscription)
    }

    override fun onCleared() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.clear()
        super.onCleared()
    }
}