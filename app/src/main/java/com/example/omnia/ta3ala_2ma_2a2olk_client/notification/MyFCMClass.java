package com.example.omnia.ta3ala_2ma_2a2olk_client.notification;

/**
 * Created by abdalla on 6/6/2018.
 */

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFCMClass extends FirebaseMessagingService {

    private final String TAG = "JSA-FCM";

    private NotificationUtils mNotificationUtils;

    SharedPreferences userDetails;

    SharredPreferenceManager manager;
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {




        if (remoteMessage.getNotification() != null) {



            Log.e(TAG, "Title: " + remoteMessage.getNotification().getTitle());
            Log.e(TAG, "Body: " + remoteMessage.getNotification().getBody());
            if (remoteMessage.getData().size() > 0) {

               if( remoteMessage.getData().get("getPersonId")!=null){
                   manager = new SharredPreferenceManager(getApplicationContext());
                   userDetails = getSharedPreferences("LoginPref", this.MODE_PRIVATE);
               String idstr = manager.getString(userDetails, "id", "0");

                    if(!remoteMessage.getData().get("getPersonId").equals(idstr)){
                        mNotificationUtils = new NotificationUtils(this);

                        Notification.Builder nb = mNotificationUtils.
                                getAndroidChannelNotification(remoteMessage.getNotification().getTitle(),
                                        remoteMessage.getNotification().getBody());

                        mNotificationUtils.getManager().notify(101, nb.build());
                    }
               }


                mNotificationUtils = new NotificationUtils(this);

                Notification.Builder nb = mNotificationUtils.
                        getAndroidChannelNotification(remoteMessage.getNotification().getTitle(),
                                remoteMessage.getNotification().getBody());

                mNotificationUtils.getManager().notify(101, nb.build());
            }
        }

        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data: " + remoteMessage.getData());
        }

    }

}