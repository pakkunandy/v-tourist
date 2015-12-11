package com.group5.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.group5.model.Place;
import com.group5.parser.DataParser;
import com.group5.service.CityServices;
import com.group5.service.PlaceServices;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

import java.util.ArrayList;
import java.util.List;


public class FullScreenViewActivity extends Activity{

    private FullScreenImageAdapter adapter;
    private ViewPager viewPager;
    private ArrayList<String> arrayListImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        viewPager = (ViewPager) findViewById(R.id.pager);

        Intent i = getIntent();
        //int position = i.getIntExtra("position", 0);
        int position = i.getExtras().getInt("id");
        adapter = new FullScreenImageAdapter(FullScreenViewActivity.this);

        viewPager.setAdapter(adapter);

        // displaying selected image first
        viewPager.setCurrentItem(position);
    }


}