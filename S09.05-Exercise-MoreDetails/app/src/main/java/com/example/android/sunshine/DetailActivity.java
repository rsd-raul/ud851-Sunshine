/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.SunshineDateUtils;
import com.example.android.sunshine.utilities.SunshineWeatherUtils;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
//      TODO (x21) Implement LoaderManager.LoaderCallbacks<Cursor>

    /*
     * In this Activity, you can share the selected day's forecast. No social sharing is complete
     * without using a hashtag. #BeTogetherNotTheSame
     */
    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    //  TODO (x18) Create a String array containing the names of the desired data columns from our ContentProvider
    private String[] WEATHER_DB_COLUMS = {
            WeatherContract.WeatherEntry.COLUMN_DATE,
            WeatherContract.WeatherEntry.COLUMN_WEATHER_ID,
            WeatherContract.WeatherEntry.COLUMN_MAX_TEMP,
            WeatherContract.WeatherEntry.COLUMN_MIN_TEMP,
            WeatherContract.WeatherEntry.COLUMN_HUMIDITY,
            WeatherContract.WeatherEntry.COLUMN_WIND_SPEED,
            WeatherContract.WeatherEntry.COLUMN_DEGREES,
            WeatherContract.WeatherEntry.COLUMN_PRESSURE
    };

    //  TODO (x19) Create constant int values representing each column name's position above
    // Assign them an int value
    public static final int INDEX_COLUMN_DATE = 0,
                            INDEX_COLUMN_WEATHER_ID = 1,
                            INDEX_COLUMN_MAX_TEMP = 2,
                            INDEX_COLUMN_MIN_TEMP = 3,
                            INDEX_COLUMN_HUMIDITY = 4,
                            INDEX_COLUMN_WIND_SPEED = 5,
                            INDEX_COLUMN_WIND_DEGREES = 6,
                            INDEX_COLUMN_PRESSURE = 7
    ;


    //  TODO (x20) Create a constant int to identify our loader used in DetailActivity
    private final int DETAILS_LOADER_ID = 128;

    /* A summary of the forecast that can be shared by clicking the share button in the ActionBar */
    private String mForecastSummary;

//  TODO (x15) Declare a private Uri field called mUri
    private Uri mUri;

//  TODO (x10) Remove the mWeatherDisplay TextView declaration

//  TODO (x11) Declare TextViews for the date, description, high, low, humidity, wind, and pressure
    private TextView dateTextView, descriptionTextView, highTextView, lowTextView,
        humidityTextView, windTextView, pressureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//      TODO (x12) Remove mWeatherDisplay TextView
//      TODO (x13) Find each of the TextViews by ID
        dateTextView = (TextView) findViewById(R.id.tv_day_date);
        descriptionTextView = (TextView) findViewById(R.id.tv_day_description);
        humidityTextView = (TextView) findViewById(R.id.tv_day_humidity);
        highTextView = (TextView) findViewById(R.id.tv_day_max_temp);
        lowTextView = (TextView) findViewById(R.id.tv_day_min_temp);
        pressureTextView = (TextView) findViewById(R.id.tv_day_pressure);
        windTextView = (TextView) findViewById(R.id.tv_day_wind);

//      TODO (x14) Remove the code that checks for extra text
//      TODO (x16) Use getData to get a reference to the URI passed with this Activity's Intent
        mUri = getIntent().getData();

//      TODO (x17) Throw a NullPointerException if that URI is null
        if(mUri == null)
            throw new NullPointerException("The Uri sent is null");
//      TODO (x35) Initialize the loader for DetailActivity
        getSupportLoaderManager().initLoader(DETAILS_LOADER_ID, null, this);
    }

    /**
     * This is where we inflate and set up the menu for this Activity.
     *
     * @param menu The options menu in which you place your items.
     *
     * @return You must return true for the menu to be displayed;
     *         if you return false it will not be shown.
     *
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.forecast, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    /**
     * Callback invoked when a menu item was selected from this Activity's menu. Android will
     * automatically handle clicks on the "up" button for us so long as we have specified
     * DetailActivity's parent Activity in the AndroidManifest.
     *
     * @param item The menu item that was selected by the user
     *
     * @return true if you handle the menu click here, false otherwise
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* Get the ID of the clicked item */
        int id = item.getItemId();

        /* Settings menu item clicked */
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        /* Share menu item clicked */
        if (id == R.id.action_share) {
            Intent shareIntent = createShareForecastIntent();
            startActivity(shareIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Uses the ShareCompat Intent builder to create our Forecast intent for sharing.  All we need
     * to do is set the type, text and the NEW_DOCUMENT flag so it treats our share as a new task.
     * See: http://developer.android.com/guide/components/tasks-and-back-stack.html for more info.
     *
     * @return the Intent to use to share our weather forecast
     */
    private Intent createShareForecastIntent() {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(mForecastSummary + FORECAST_SHARE_HASHTAG)
                .getIntent();
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        return shareIntent;
    }

    // TODO (x22) Override onCreateLoader
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // TODO (x23) If the loader requested is our detail loader, return the appropriate CursorLoader
        if(id != DETAILS_LOADER_ID)
            throw new RuntimeException("Loader Not Implemented: " + id);

        return new CursorLoader(this,
                mUri,               // The Uri already points to the exact item
                WEATHER_DB_COLUMS,  // Array of the columns we need to retrieve
                null, null, null);
    }

    // TODO (x24) Override onLoadFinished
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//      TODO (x25) Check before doing anything that the Cursor has valid data
        if (data == null || data.getCount() == 0) {
            /* No data to display, simply return and do nothing */
            return;
        }
        /* We have valid data, point the cursor to the first row and continue on to bind the data to the UI */
        data.moveToFirst();
        
//      TODO (x26) Display a readable data string
        // Access data with the indexes defined before
        long dateIndex = data.getLong(INDEX_COLUMN_DATE);
        String date = SunshineDateUtils.getFriendlyDateString(this, dateIndex, true);
        dateTextView.setText(date);

//      TODO (x27) Display the weather description (using SunshineWeatherUtils)
        int weatherInt = data.getInt(INDEX_COLUMN_WEATHER_ID);
        String description = SunshineWeatherUtils.getStringForWeatherCondition(this, weatherInt);
        descriptionTextView.setText(description);

//      TODO (x28) Display the high temperature
//      TODO (x29) Display the low temperature
        double high = data.getDouble(INDEX_COLUMN_MAX_TEMP);
        double low = data.getDouble(INDEX_COLUMN_MIN_TEMP);
        String highAndLow = SunshineWeatherUtils.formatHighLows(this, high, low);
        highTextView.setText(SunshineWeatherUtils.formatTemperature(this, high));
        lowTextView.setText(SunshineWeatherUtils.formatTemperature(this, low));

//      TODO (x30) Display the humidity
        float humidity = data.getFloat(INDEX_COLUMN_HUMIDITY);
        humidityTextView.setText(getString(R.string.format_humidity, humidity));

//      TODO (x31) Display the wind speed and direction
        float speed = data.getFloat(INDEX_COLUMN_WIND_SPEED);
        float degrees = data.getFloat(INDEX_COLUMN_WIND_DEGREES);
        windTextView.setText(SunshineWeatherUtils.getFormattedWind(this, speed, degrees));

//      TODO (x32) Display the pressure
        float pressure = data.getFloat(INDEX_COLUMN_PRESSURE);
        pressureTextView.setText(getString(R.string.format_pressure, pressure));

//      TODO (x33) Store a forecast summary in mForecastSummary
        mForecastSummary = date + " - " + description + " - " + highAndLow;
    }

    //  TODO (x34) Override onLoaderReset, but don't do anything in it yet
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {}
}