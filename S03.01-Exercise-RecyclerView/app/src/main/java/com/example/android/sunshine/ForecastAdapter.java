package com.example.android.sunshine;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

// TODO (x15) Add a class file called ForecastAdapter
// TODO (x22) Extend RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>{

    // TODO (x23) Create a private string array called mWeatherData
    private String[] mWeatherData;

    // TODO (x47) Create the default constructor (we will pass in parameters in a later lesson)
    public ForecastAdapter() {
    }

    // TODO (x16) Create a class within ForecastAdapter called ForecastAdapterViewHolder
    // TODO (x17) Extend RecyclerView.ViewHolder
    class ForecastAdapterViewHolder extends RecyclerView.ViewHolder{

        // TODO (x18) Create a public final TextView variable called mWeatherTextView
        public final TextView mWeatherTextView;

        // TODO (x19) Create a constructor for this class that accepts a View as a parameter
        public ForecastAdapterViewHolder(View itemView) {
            // TODO (x20) Call super(view) within the constructor for ForecastAdapterViewHolder
            // TODO (x21) Using view.findViewById, get a reference to this layout's TextView and save it to mWeatherTextView
            super(itemView);
            mWeatherTextView = (TextView) itemView.findViewById(R.id.tv_weather_data);
        }
    }

    // TODO (x24) Override onCreateViewHolder
    // TODO (x25) Within onCreateViewHolder, inflate the list item xml into a view
    // TODO (x26) Within onCreateViewHolder, return a new ForecastAdapterViewHolder with the above view passed in as a parameter
    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.forecast_list_item, parent, false);
        return new ForecastAdapterViewHolder(view);
    }

    // TODO (x27) Override onBindViewHolder
    // TODO (x28) Set the text of the TextView to the weather for this list item's position
    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {
        holder.mWeatherTextView.setText(mWeatherData[position]);
    }

    // TODO (x29) Override getItemCount
    // TODO (x30) Return 0 if mWeatherData is null, or the size of mWeatherData if it is not null
    @Override
    public int getItemCount() {
        return mWeatherData == null ? 0 : mWeatherData.length;
    }


    // TODO (x31) Create a setWeatherData method that saves the weatherData to mWeatherData
    // TODO (x32) After you save mWeatherData, call notifyDataSetChanged
    public void setWeatherData(String[] mWeatherData) {
        this.mWeatherData = mWeatherData;
        notifyDataSetChanged();
    }
}