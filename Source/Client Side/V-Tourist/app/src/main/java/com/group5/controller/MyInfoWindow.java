package com.group5.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.group5.model.Place;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

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
        TextView btnDirection = (Button)view.findViewById(R.id.btnDirection);
        ImageView imgImageLocation = (ImageView)view.findViewById(R.id.imgImageLocation);


        lblLocationName.setText(place.getPlaceName());
        lblLocationAddress.setText(place.getAddress());





        return  view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
