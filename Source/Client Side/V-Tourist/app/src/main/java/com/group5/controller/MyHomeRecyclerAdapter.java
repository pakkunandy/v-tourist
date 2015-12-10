package com.group5.controller;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group5.model.Place;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

import java.util.ArrayList;

/**
 * Created by Salmon on 11/24/2015.
 * Adapter for recycler view
 */
public class MyHomeRecyclerAdapter extends RecyclerView.Adapter<MyHomeRecyclerAdapter.ViewHolder> {


    ArrayList<Place> arrayList;
    Context context;
    int layoutId;


    //Construtor
    public MyHomeRecyclerAdapter(Context context, int layoutId, ArrayList<Place> arrayList) {
        super();
        this.context = context;
        this.layoutId = layoutId;
        this.arrayList = arrayList;

        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this.context)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
    }

    @Override
    public MyHomeRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new MyHomeRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHomeRecyclerAdapter.ViewHolder holder, final int position) {


        holder.captionTitle.setText(arrayList.get(position).getPlaceName());
        holder.captionBody.setText(arrayList.get(position).getPlaceDescription());

        DisplayImageOptions dio = new DisplayImageOptions.Builder().displayer(new BitmapDisplayer() {
            @Override
            public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
                int gradientStartColor = Color.argb(0, 0, 0, 0);
                int gradientEndColor = Color.argb(255, 0, 0, 0);
                GradientOverImageDrawable gradientDrawable = new GradientOverImageDrawable(context.getResources(), bitmap);
                gradientDrawable.setGradientColors(gradientStartColor, gradientEndColor);
                imageAware.setImageDrawable(gradientDrawable);
            }
        })
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
        ImageLoader.getInstance().displayImage(arrayList.get(position).firstImageURL, holder.imgTest, dio);

        holder.imgTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable.idGlobalPlaceCurrent = arrayList.get(position).getPlaceId();
                GlobalVariable.longtitute = arrayList.get(position).getLongitude();
                GlobalVariable.latitute = arrayList.get(position).getLatitude();
                GlobalVariable.name = arrayList.get(position).getPlaceName();
                Intent intent = new Intent(context.getApplicationContext(), DetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //declare something control matching with layout viewholder
        public ImageView imgTest;
        public TextView captionTitle;
        public TextView captionBody;

        public ViewHolder(View itemView) {
            super(itemView);

            imgTest = (ImageView) itemView.findViewById(R.id.imgTest);
            captionTitle = (TextView) itemView.findViewById(R.id.captionTitleView);
            captionBody = (TextView) itemView.findViewById(R.id.captionTextView);
        }
    }
}
