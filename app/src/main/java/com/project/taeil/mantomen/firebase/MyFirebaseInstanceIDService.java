package com.project.taeil.mantomen.firebase;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    private final static String TAG = "FCM_ID";

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);

        Log.e("Refreshed token:",token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }
}