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

import android.provider.BaseColumns;

/**
 * Defines table and column names for the weather database. This class is not necessary, but keeps
 * the code organized.
 */
public final class WeatherContract {

//  TODO (x1) Within WeatherContract, create a public static final class called WeatherEntry that implements BaseColumns
    public static final class WeatherEntry implements BaseColumns{

    //      TODO (x2) Create a public static final String call TABLE_NAME with the value "weather"
    //      TODO (x3) Create a public static final String call COLUMN_DATE with the value "date"
    //      TODO (x4) Create a public static final String call COLUMN_WEATHER_ID with the value "weather_id"
    //      TODO (x5) Create a public static final String call COLUMN_MIN_TEMP with the value "min"
    //      TODO (x6) Create a public static final String call COLUMN_MAX_TEMP with the value "max"
    //      TODO (x7) Create a public static final String call COLUMN_HUMIDITY with the value "humidity"
    //      TODO (x8) Create a public static final String call COLUMN_PRESSURE with the value "pressure"
    //      TODO (x9) Create a public static final String call COLUMN_WIND_SPEED with the value "wind"
    //      TODO (x10) Create a public static final String call COLUMN_DEGREES with the value "degrees"
        public static final String TABLE_NAME = "weather",
            COLUMN_DATE = "date",
            COLUMN_WEATHER_ID = "weather_id",
            COLUMN_MIN_TEMP = "min",
            COLUMN_MAX_TEMP = "max",
            COLUMN_HUMIDITY = "humidity",
            COLUMN_PRESSURE = "pressure",
            COLUMN_WIND_SPEED = "wind",
            COLUMN_DEGREES = "degrees";
    }
}