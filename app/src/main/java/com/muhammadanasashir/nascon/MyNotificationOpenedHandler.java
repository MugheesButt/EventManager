package com.muhammadanasashir.nascon;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

class MyNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    private Context mContext;
    public MyNotificationOpenedHandler(Context mContext) {
        this.mContext = mContext;
    }
    @Override
    public void notificationOpened(OSNotificationOpenResult
                                           result) {
        OSNotificationAction.ActionType actionType =
                result.action.type;
        JSONObject data =
                result.notification.payload.additionalData;
        String customKey;
        Log.i("OSNotificationPayload",
                "result.notification.payload.toJSONObject().toString(): " +
                        result.notification.payload.toJSONObject().toString());
        if (data != null) {
            customKey = data.optString("customkey", null);
            if (customKey != null)
                Log.i("OneSignalExample", "customkey set with value: " + customKey);
        }
        if (actionType ==
                OSNotificationAction.ActionType.ActionTaken)
            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);
                    Intent intent = new Intent(mContext, Notifications.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}