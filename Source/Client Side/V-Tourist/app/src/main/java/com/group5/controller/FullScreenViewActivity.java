package com.group5.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;


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
        int position = i.getExtras().getInt("imageUrl");
        adapter = new FullScreenImageAdapter(FullScreenViewActivity.this);

        viewPager.setAdapter(adapter);

        // displaying selected image first
        viewPager.setCurrentItem(position);
    }


}