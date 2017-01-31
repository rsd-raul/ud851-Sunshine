package com.example.android.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.sunshine.utilities.SunshineWeatherUtils;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView weatherTextView = (TextView) findViewById(R.id.weather_data_details);
        // TODO (x2) Display the weather forecast that was passed from MainActivity
        String weather = getIntent().getStringExtra(SunshineWeatherUtils.WEATHER);

        if(weather != null && !weather.equals(""))
            weatherTextView.setText(weather);
    }
}