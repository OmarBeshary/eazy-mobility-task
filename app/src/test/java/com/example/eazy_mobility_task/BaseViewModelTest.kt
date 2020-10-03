package com.example.eazy_mobility_task

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.eazy_mobility_task.common.api.TripsApi
import com.example.eazy_mobility_task.common.component.states.IViewState
import com.example.eazy_mobility_task.common.component.states.Result
import com.example.eazy_mobility_task.common.koin.*
import com.example.eazy_mobility_task.common.model.response.TripInfoResponse
import com.example.eazy_mobility_task.common.network.IRetrofitClient
import com.example.eazy_mobility_task.common.network.RetrofitClient
import com.example.eazy_mobility_task.common.repo.TripsRepo
import com.example.eazy_mobility_task.common.services.SchedulersService
import com.example.eazy_mobility_task.features.TripsViewModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.inject
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class BaseViewModelTest : KoinTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(schedulerModule, repoModules, restAPIsModules, restClientModule, viewModelModules)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    private val viewModel: TripsViewModel by inject()

    @Mock
    lateinit var observerData: Observer<IViewState<TripInfoResponse>>

    @Test
    fun `declareMock with KoinTest`() {
        // Arrange
        viewModel.latestTripStates.observeForever(observerData)
        val tripsInfoResponse = TripInfoResponse(
            id = 1,
            pickupLat = 1.00,
            pickupLng = 1.23,
            dropOffStation = "CIB",
            pickUpStation = "Maadi Station",
            dropOffLat = 1.3,
            dropOffLng = 3.0
        )
        val single = Single.just(tripsInfoResponse)
        declareMock<TripsRepo> {
            given(getLatestTrip())
                .willReturn(single)
        }
        declareMock<SchedulersService> {
            given(getIOThread())
                .willReturn(Schedulers.trampoline())
            given(getAndroidMainThread())
                .willReturn(Schedulers.trampoline())
        }
        declareMock<TripsApi> {
            given(getLatestTrip())
                .willReturn(single)
        }
        // Act
        viewModel.getLatestTrip()
        // Assert
        assertEquals(viewModel.latestTripStates.value, Result.success(tripsInfoResponse))
//        Mockito.verify(observerData).onChanged(tripsInfoResponse)
    }
}