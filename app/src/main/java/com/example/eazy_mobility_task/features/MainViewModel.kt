package com.example.eazy_mobility_task.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eazy_mobility_task.common.repo.MainRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * TODO :
 * remove fixed thread call
 * add disposal
 * add IViewState
 * add unit testing
 * add offline support
 * add kotlin code ktx
 * add error code
 * _ _ _ _ _ _ _ _ _ _ _
 *
 */
class MainViewModel(private val repo: MainRepo) : ViewModel() {

    private val state: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getFacts() {
        repo.getFacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            },{

            })
    }

    fun getStates(): LiveData<String> = state
}