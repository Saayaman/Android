package com.ayako_sayama.googlemapsapi;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback{

    MapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(MapActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 5))
                .title("Marker 2"));
        map.addPolyline(new PolylineOptions().add(new LatLng(0, 0)).add(new LatLng(0, 5)));
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setTiltGesturesEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        map.setBuildingsEnabled(true);

    }
}
