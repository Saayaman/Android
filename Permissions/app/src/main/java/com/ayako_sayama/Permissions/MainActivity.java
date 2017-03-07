package com.ayako_sayama.Permissions;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ayako_sayama.practice.R;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

public class MainActivity extends AppCompatActivity {

    static final Integer LOCATION = 0x1;
    static final Integer CALL = 0x2;
    static final Integer WRITE_EXST = 0x3;
    static final Integer READ_EXST = 0x4;
    static final Integer CAMERA = 0x5;
    static final Integer ACCOUNTS = 0x6;
    static final Integer GPS_SETTINGS = 0x7;
    static final String TAG = "MainActivity";

    GoogleApiClient client;
    LocationRequest locationRequest;
    PendingResult<LocationSettingsResult> result;
    LocationSettingsRequest.Builder locationBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new GoogleApiClient.Builder(this)
                .addApi(AppIndex.API)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    protected void onStart() {
        super.onStart();
        client.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        client.disconnect();
    }

    public void ask(View view) {

        switch (view.getId()) {
            case R.id.location:
                askForPermission(Manifest.permission.ACCESS_FINE_LOCATION, LOCATION);
                break;
            case R.id.call:
                askForPermission(Manifest.permission.CALL_PHONE, CALL);
                break;
            case R.id.write:
                askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_EXST);
                break;
            case R.id.read:
                askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, READ_EXST);
                break;
            case R.id.camera:
                askForPermission(Manifest.permission.CAMERA, CAMERA);
                break;
            case R.id.account:
                askForPermission(Manifest.permission.GET_ACCOUNTS, ACCOUNTS);
                break;
        }
    }

    private void askForPermission(String permission, Integer requestCode) {

        //Check if Permission is granted, and if NOT
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            //Check if permission is cancelled before, if yes, ask for permission anyway
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

            }

            //if it is the first time, ask away
            else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }

        }

        //CHECK, and if granted already
        else {
            Toast.makeText(this, "Permission is Already Granted", Toast.LENGTH_SHORT).show();
        }
    }

    //is called whenever, you press cancel or OK for permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //if Permission Dialog is pressed OK
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {

            //Detect which permission it is, and grant permission
            grantPermission(requestCode);
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();

        }

        //if Permission Dialog is Pressed Cancel.
        else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    private void grantPermission(Integer requestCode) {
        switch (requestCode) {
            case 1:
                askForGPS();
                break;
            case 2: //call
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel: " + "{The Telephone Number}"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                }
              break;
          case 3: //write External Storage
              break;
          case 4: //read External Storage
              Intent imageIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
              startActivityForResult(imageIntent,11);
            break;
          case 5: //camera
               Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              startActivityForResult(takePicIntent, 12);

              break;
          case 6: //accounts
              AccountManager manager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
              Account[] list = manager.getAccounts();
              Toast.makeText(this,""+list[0].name,Toast.LENGTH_SHORT).show();
              for(int i=0; i<list.length;i++){
                  Log.e("Account "+i,""+list[i].name);
              }
              break;
      }

    }


    private void askForGPS() {
       locationRequest =  LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationBuilder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        locationBuilder.setAlwaysShow(true);

        result = LocationServices.SettingsApi.checkLocationSettings(client,locationBuilder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();

                int statusCode = status.getStatusCode();

                if (statusCode == LocationSettingsStatusCodes.SUCCESS){

                }
                else if (statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED){
                  try {
                      status.startResolutionForResult(MainActivity.this, GPS_SETTINGS);
                  }catch (IntentSender.SendIntentException e){

                  }
                }
                else if (statusCode == LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE){

                }
            }
        });
    }
}
