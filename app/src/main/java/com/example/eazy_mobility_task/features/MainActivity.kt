package com.example.eazy_mobility_task.features

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.directions.route.Routing
import com.example.eazy_mobility_task.R
import com.example.eazy_mobility_task.common.RouteUtil

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private val viewModel: MainViewModel = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getFacts()
        initViews()
    }

    private fun initViews() {
        val mapFragment =
                supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
    }

    private fun onRoutePointsAreReady(startLatLng: LatLng, endLatLng: LatLng) {
        RouteUtil.drawRoute(
            startLatLng, endLatLng
        ) { points ->
            drawPolyLineFromStartToEndPoint(points)
        }
    }

    private fun drawPolyLineFromStartToEndPoint(point: List<LatLng>) {

    }

}