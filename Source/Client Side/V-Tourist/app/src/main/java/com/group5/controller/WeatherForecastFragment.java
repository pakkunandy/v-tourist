package com.group5.controller;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group5.controller.WeatherAdapter.C16Information;
import com.group5.controller.WeatherAdapter.Menu16ItemAdapter;
import com.group5.model.C16DayForecastsModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Duy on 12/15/2015.
 */
public class WeatherForecastFragment extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_forecast, container, false);

        C16DayForecastsAsyncTask c16DayForecastsAsyncTask = new C16DayForecastsAsyncTask(view);
        c16DayForecastsAsyncTask.execute();

        return view;
    }

    /**
     * AsyncTask
     */
    public class C16DayForecastsAsyncTask extends AsyncTask<String, Void, C16DayForecastsModel> {
        View view;

        private RecyclerView recyclerView;
        private Menu16ItemAdapter adapter;
        ProgressDialog progressDialog;

        public C16DayForecastsAsyncTask(View view) {
            this.view = view;
            progressDialog = new ProgressDialog(view.getContext());
            progressDialog.setTitle("Loading");
            progressDialog.show();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected C16DayForecastsModel doInBackground(String... params) {

            WeatherHelper weatherHelper = new WeatherHelper();
            //Set Coordinate
            return weatherHelper.get16DayForecastsbyCoordinate(GlobalVariable.longtitute.toString(),
                    GlobalVariable.latitute.toString(),
                    "10");
        }

        @Override
        protected void onPostExecute(C16DayForecastsModel o) {
            super.onPostExecute(o);
            recyclerView = (RecyclerView) view.findViewById(R.id.render16DayForecast);
            adapter = new Menu16ItemAdapter(view.getContext(), getData(o));

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

            progressDialog.dismiss();
        }

        public List<C16Information> getData(C16DayForecastsModel modelData) {
            List<C16Information> lstData = new ArrayList<>();
            int size = modelData.lstDayForecasts.size();
            for (int i = 0; i < size; ++i) {
                C16Information item = new C16Information();
                item.date = convertDate(Long.parseLong(modelData.lstDayForecasts.get(i).dttxt));
                item.mainWeather = modelData.lstDayForecasts.get(i).mMainWeather;
                item.mainWeatherDesc = modelData.lstDayForecasts.get(i).mWeatherDescription;
                item.humi = String.valueOf(modelData.lstDayForecasts.get(i).mHumidity);
                item.press = String.valueOf(modelData.lstDayForecasts.get(i).mPressure);
                item.windSpeed = String.valueOf(modelData.lstDayForecasts.get(i).mWindSpeed);
                item.iconID = modelData.lstDayForecasts.get(i).mIconid;
                item.temp = String.valueOf((int) modelData.lstDayForecasts.get(i).mTempDay);

                lstData.add(item);
            }
            return lstData;
        }


        private String convertDate(long date) {
            Date dt = new Date(date * 1000);
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM");
            String dateText = df2.format(dt);
            return dateText;
        }
    }
}

