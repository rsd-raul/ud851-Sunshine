package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import java.net.URL;
import java.sql.SQLDataException;

//  TODO (x1) Create a class called SunshineSyncTask
public class SunshineSyncTask{

    private static final String TAG = "SunshineSyncTask";

    //  TODO (x2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
    synchronized public static void syncWeather(Context context){

        try {
            // TODO (x3) Within syncWeather, fetch new weather data
            // Query the server for data
            URL url = NetworkUtils.getUrl(context);
            String response = NetworkUtils.getResponseFromHttpUrl(url);

            // Extract data from the response
            ContentValues[] values = OpenWeatherJsonUtils
                                                .getWeatherContentValuesFromJson(context, response);

            // Report the error if we don't receive info
            if(values == null || values.length == 0)
                throw new SQLDataException("Empty or null response");

            // TODO (x4) If we have valid results, delete the old data and insert the new
            ContentResolver contentResolver = context.getContentResolver();

            // Delete all data from the DB
            contentResolver.delete(WeatherContract.WeatherEntry.CONTENT_URI, null, null);

            // Insert all new items in the DB
            contentResolver.bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI, values);

        }catch (Exception ex){
            Log.e(TAG, "syncWeather: exception while fetching/storing data", ex);
        }
    }
}
