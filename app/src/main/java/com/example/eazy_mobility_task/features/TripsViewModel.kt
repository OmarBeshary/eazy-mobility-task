package com.example.eazy_mobility_task.features

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eazy_mobility_task.common.component.states.IViewState
import com.example.eazy_mobility_task.common.component.view_model.BaseViewModel
import com.example.eazy_mobility_task.common.model.response.TripInfoResponse
import com.example.eazy_mobility_task.common.repo.TripsRepo

/**
 * TODO :
 * integrate with backend
 * UI experience
 * add unit testing
 * add offline support
 * add error code
 * add build config file
_ _ _ _ _ _ _ _ _ _ _
 * remove fixed thread call
 * add disposal
 * show route
 * add IViewState
 * add base viewModel
 * UI enhancement
 * add kotlin code ktx/gradle versions [continue ..]
 */
class TripsViewModel(
    private val repo: TripsRepo
) : BaseViewModel() {

    private val latestTripStates: MutableLiveData<IViewState<TripInfoResponse>> by lazy {
        MutableLiveData<IViewState<TripInfoResponse>>()
    }

    private val tripInfoStates: MutableLiveData<IViewState<TripInfoResponse>> by lazy {
        MutableLiveData<IViewState<TripInfoResponse>>()
    }

    private fun get1stTrip() {

    }

    private fun getPreviousTrip() {

    }

    private fun getNextTrip() {

    }

    private fun getLastTrip() {

    }

    fun getLatestTrip() {
        repo
            .getLatestTrip()
            .execute(latestTripStates)
    }

    private fun getTripInfo(id: Long) {
        repo
            .getTripInfo(id)
            .execute(tripInfoStates)
    }

    fun observeLatestTripInfoStates(): LiveData<IViewState<TripInfoResponse>> =
        latestTripStates


}