package com.ayako_sayama.googlemapsapi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";
    LocationManager locationManager;
    LocationListener locationListener;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                textView.setText("New Location:" +
                        "Latitude: " + location.getLatitude() + "\n" +
                        "Longitude: " + location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
    }

    //GPS MAPのトラッキングを再開！
    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);


        } else {
        //GPSがNO-権限だったらpermission をrequestする〜
                        ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 123);

        }

    }

    //アプリを停止したら、GPSトラッキングを停止：電池を食うため

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.removeUpdates(locationListener);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.i(TAG, "onRequestPermissionsResult: " + grantResults.length);

        if (requestCode == 123) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            }
        } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "Permittion Denied", Toast.LENGTH_SHORT).show();
        }
    }


    public void startMapWithMyLocation(View v) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            Toast.makeText(this, "Turn on Location or Die asshole!", Toast.LENGTH_SHORT).show();
            return;
        }
        //GPS_PROVIDERとNETWORK_PROVIDERどちらもあるよ！
//        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        Log.i(TAG, "startMapWithMyLocation: This is clicked!!");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+location.getLatitude()+","+location.getLongitude()
                +"?q=" + location.getLatitude()+","+location.getLongitude()));
        //geo: 38.000000,34000000?q=38.000000,34000000
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);

    }

    public void startBuiltInMaps(View v)
    {
        Intent intent2 = new Intent(getApplicationContext(), MapActivity.class);
        startActivity(intent2);
    }


    private void askForPermission(String permission, Integer requestCode){

       // first check if the permission is granted: if it is NOT, execute inside:
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) !=  PackageManager.PERMISSION_GRANTED) {

            //This is called if user has denied the permission before
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,permission)){

                Toast.makeText(this, "Permission still not granted, you really need to grant it", Toast.LENGTH_SHORT).show();
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{permission}, requestCode);
            }else{

                //This is called when you access first time
                Toast.makeText(this, "Permission is not granted", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }

        }
        //exute this if permission is granted already
        else {
             Toast.makeText(this,"" + permission + " is already granted.",Toast.LENGTH_SHORT).show();
        }
    }
}
