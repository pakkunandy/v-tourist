package com.group5.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.group5.model.Image;
import com.group5.model.Place;
import com.group5.service.PlaceServices;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.parse.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Salmon on 12/3/2015.
 */


public class MyInfoWindow implements GoogleMap.InfoWindowAdapter {

    Context context;
    int layoutId;
    Place place;

    public MyInfoWindow(Context context, int layoutId, Place place){
        super();
        this.context = context;
        this.layoutId = layoutId;
        this.place = place;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View  view = layoutInflater.inflate(layoutId, null);

        TextView lblLocationName = (TextView)view.findViewById(R.id.lblLocationName);
        TextView lblLocationAddress = (TextView)view.findViewById(R.id.lblLocationAddress);

        lblLocationName.setText(place.getPlaceName());
        lblLocationAddress.setText(place.getAddress());



        return  view;
    }


    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
