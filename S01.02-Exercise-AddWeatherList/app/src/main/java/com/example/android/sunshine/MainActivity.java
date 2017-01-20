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
package com.example.android.sunshine;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;
import com.example.android.sunshine.utilities.SunshineDateUtils;
import com.example.android.sunshine.utilities.SunshineWeatherUtils;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // TODO (x1) Create a field to store the weather display TextView
    private TextView weatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        weatherTextView = (TextView) findViewById(R.id.tv_weather_data);

        String[] days = {"Today", "Tomorrow", "April 21 ", "April 22", "April 23", "April 24"};
        String[] states = {"Clear", "Cloudy", "Rainy", "Thunderstorms", "Partly Cloudy", "Stormy"};

        Random ran = new Random();
        int max, min;
        String state;

        for (String day : days) {
            // Dummy temperature generator
            min = 3 + ran.nextInt(31);      // Range 3 to 33
            max = min + ran.nextInt(11);    // Range min to min+10
            state = states[ran.nextInt(states.length)];

            weatherTextView.append(max + "ºC / " + min + "ºC - " + state + " - " + day + "\n");
        }

        // TODO (x2) Use findViewById to get a reference to the weather display TextView
        // TODO (x3) Create an array of Strings that contain fake weather data
        // TODO (x4) Append each String from the fake weather data array to the TextView
    }
}