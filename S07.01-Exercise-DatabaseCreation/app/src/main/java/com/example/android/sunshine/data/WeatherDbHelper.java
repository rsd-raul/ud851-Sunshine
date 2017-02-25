/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.example.android.sunshine.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.android.sunshine.data.WeatherContract.WeatherEntry.*;

/**
 * Manages a local database for weather data.
 */
// TODO (x11) Extend SQLiteOpenHelper from WeatherDbHelper
public class WeatherDbHelper extends SQLiteOpenHelper{

    //  TODO (x12) Create a public static final String called DATABASE_NAME with value "weather.db"
    private static final String DATABASE_NAME = "waitlist.db";
    //  TODO (x13) Create a private static final int called DATABASE_VERSION and set it to 1
    private static final int DATABASE_VERSION = 1;

    //  TODO (x14) Create a constructor that accepts a context and call through to the superclass constructor
    public WeatherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //  TODO (x15) Override onCreate and create the weather table from within it
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_WEATHER_TABLE  = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID                 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE         + " INTEGER, " +
                COLUMN_WEATHER_ID   + " INTEGER, " +
                COLUMN_MIN_TEMP     + " REAL, " +
                COLUMN_MAX_TEMP     + " REAL, " +
                COLUMN_HUMIDITY     + " REAL, " +
                COLUMN_PRESSURE     + " REAL, " +
                COLUMN_WIND_SPEED   + " REAL, " +
                COLUMN_DEGREES      + " REAL);";

        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
    }

    //  TODO (x16) Override onUpgrade, but don't do anything within it yet
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }
}