package com.example.android.sunshine.sync;

import android.content.Context;
import android.content.Intent;

// TODO (x9) Create a class called SunshineSyncUtils
public class SunshineSyncUtils {

    //  TODO (x10) Create a public static void method called startImmediateSync
    public static void startImmediateSync(Context context){
        //  TODO (x11) Within that method, start the SunshineSyncIntentService
        Intent service = new Intent(context, SunshineSyncIntentService.class);
        context.startService(service);
    }
}