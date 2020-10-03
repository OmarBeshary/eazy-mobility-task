package com.example.eazy_mobility_task.features.trip_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eazy_mobility_task.common.component.states.IViewState
import com.example.eazy_mobility_task.common.component.view_model.BaseViewModel
import com.example.eazy_mobility_task.common.model.response.TripInfoResponse
import com.example.eazy_mobility_task.common.repo.TripsRepo

/**
 * add offline support [continue ..]
 * _ _ _ _ __ _ _ _ _ _
 * add kotlin code ktx/gradle versions [Done]
 * UI experience
 * add build config file [Done]
 * GIT repo
 * _ _ _ _ __ _ _ _ _ _
 * Unit testing
 * back pressure
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

    private var currentTripId: Long = -1

    fun getLatestTrip() {
        repo
            .getLatestTrip()
            .execute(latestTripStates)
    }

    fun getTripInfo(id: Long) {
        updateCurrentTripId(id)
        repo
            .getTripInfo(id)
            .execute(tripInfoStates)
    }

    fun updateCurrentTripId(id: Long) {
        currentTripId = id
    }

    fun getCurrentTripId() = currentTripId

    fun observeLatestTripInfoStates(): LiveData<IViewState<TripInfoResponse>> =
        latestTripStates

    fun observeTripInfoStates(): LiveData<IViewState<TripInfoResponse>> =
        tripInfoStates

}