package com.example.eazy_mobility_task.common;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.example.eazy_mobility_task.BuildConfig;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MapManager {

    public static void drawRoute(LatLng startPoint, LatLng endPoint, final RouteCallBack callBack) {
        ArrayList<LatLng> wayPoints = new ArrayList<>();

        wayPoints.add(startPoint);
        wayPoints.add(endPoint);

        Routing routing = new Routing.Builder()
                .travelMode(AbstractRouting.TravelMode.DRIVING)
                .waypoints(wayPoints)
                .key(BuildConfig.GOOGLE_MAP_API_TOKEN)
                .withListener(new RoutingListener() {
                    @Override
                    public void onRoutingFailure(RouteException e) {
                        callBack.onRoutePointsLoaded(wayPoints);
                    }

                    @Override
                    public void onRoutingStart() {

                    }

                    @Override
                    public void onRoutingSuccess(ArrayList<Route> routes, int i) {
                        if (routes != null)
                            for (Route route : routes)
                                callBack.onRoutePointsLoaded(route.getPoints());
                    }


                    @Override
                    public void onRoutingCancelled() {

                    }
                }).build();
        routing.execute();
    }
    public interface RouteCallBack {
        void onRoutePointsLoaded(List<LatLng> points);
    }
}
