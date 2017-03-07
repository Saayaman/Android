package com.ayako_sayama.firebasechatapp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ayako_sayama on 2017-03-05.
 */

public class MyFireBaseInstanceService extends FirebaseInstanceIdService {
    private static final String TAG = "FireBaseInstanceService";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }


}
