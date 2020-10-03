package com.example.eazy_mobility_task.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eazy_mobility_task.R
import com.example.eazy_mobility_task.common.MapManager
import com.example.eazy_mobility_task.common.component.states.CommonStatus.SUCCESS
import com.example.eazy_mobility_task.common.model.response.TripInfoResponse
import com.example.eazy_mobility_task.common.repo.FIRST_TRIP_ID
import com.example.eazy_mobility_task.common.repo.LATEST_TRIP_ID
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get

class TripsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val viewModel: TripsViewModel = get()
    private lateinit var googleMap: GoogleMap
    private lateinit var options: PolylineOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initObservables()
        viewModel.getLatestTrip()
    }

    private fun initViews() {
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        first_trip_btn.setOnClickListener {
            on1stTripClicked()
        }
        next_trip_btn.setOnClickListener {
            onNextTripBtnClicked()
        }
        prev_trip_btn.setOnClickListener {
            onPreviousTripBtnClicked()
        }
        last_trip_btn.setOnClickListener {
            onLatestTripBtnClicked()
        }
    }

    private fun onNextTripBtnClicked() {
        with(viewModel) {
            val currentTripId = getCurrentTripId()
            getTripInfo(id = currentTripId + 1)
        }
    }

    private fun onPreviousTripBtnClicked() {
        with(viewModel) {
            val currentTripId = getCurrentTripId()
            getTripInfo(id = currentTripId - 1)
        }
    }

    private fun on1stTripClicked() {
        viewModel.getTripInfo(FIRST_TRIP_ID)
    }

    private fun onLatestTripBtnClicked() {
        viewModel.getTripInfo(LATEST_TRIP_ID)
    }


    private fun initObservables() {
        viewModel.observeLatestTripInfoStates().observe(this, { state ->
            when (state.whichState()) {
                SUCCESS -> {
                    state.fetchData()?.let { info ->
                        viewModel.updateCurrentTripId(id = info.id)
                        onRoutePointsAreReady(info)
                    }
                }
            }
        })
        viewModel.observeTripInfoStates().observe(this, { state ->
            when (state.whichState()) {
                SUCCESS -> {
                    state.fetchData()?.let {
                        onRoutePointsAreReady(it)
                    }
                }
            }
        })
    }


    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
    }

    private fun onRoutePointsAreReady(info: TripInfoResponse) {
        //updateScreenTripButtons()
        with(info) {
            val startPoint =
                LatLng(pickupLat, pickupLng)
            val endpoint =
                LatLng(dropOffLat, dropOffLng)
            pickup_tv.text = pickUpStation
            destination_tv.text = dropOffStation
            MapManager.drawRoute(
                startPoint, endpoint
            ) { points ->
                drawPolyLineFromStartToEndPoint(points)
            }

        }
    }

    private fun updateScreenTripButtons() {
        if (viewModel.getCurrentTripId() == FIRST_TRIP_ID)
            enableDisableTripButton(b1 = false, b2 = true, b3 = false, b4 = true)
        if (viewModel.getCurrentTripId() == LATEST_TRIP_ID)
            enableDisableTripButton(b1 = true, b2 = false, b3 = true, b4 = false)
        else
            enableDisableTripButton(b1 = true, b2 = true, b3 = true, b4 = true)
    }

    private fun enableDisableTripButton(b1: Boolean, b2: Boolean, b3: Boolean, b4: Boolean) {
        first_trip_btn.isEnabled = b1
        next_trip_btn.isEnabled = b2
        prev_trip_btn.isEnabled = b3
        last_trip_btn.isEnabled = b4
    }

    private fun drawPolyLineFromStartToEndPoint(points: List<LatLng>) {
        googleMap.apply {
            clear()
            uiSettings.setAllGesturesEnabled(false)
            val options = getPolyLineOptions()
            addMarker(
                MarkerOptions().position(points[0])
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pick_up_marker_icn))
            )
            val latLngBuilder = LatLngBounds.builder()
            points.forEach { latlng ->
                options.add(latlng)
                latLngBuilder.include(latlng)
            }
            addMarker(
                MarkerOptions().position(points[points.size - 1])
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.destination_address_icn))
            )
            addPolyline(options)
            val bounds = latLngBuilder.build()
            val padding = 20
            val cu = CameraUpdateFactory.newLatLngBounds(
                bounds,
                padding
            )
            moveCamera(cu)
            animateCamera(cu, 2000, null)
            enableGestures()
        }
    }

    private fun getPolyLineOptions(): PolylineOptions {
        if (!this::options.isInitialized) {
            options = PolylineOptions().apply {
                color(resources.getColor(R.color.cerulean))
                width(12.0f)
                visible(true)
            }
        } else
            options.points.clear()
        return options
    }

    private fun enableGestures() {
        googleMap.uiSettings.apply {
            isScrollGesturesEnabled = true
            isZoomGesturesEnabled = true
        }
    }

}