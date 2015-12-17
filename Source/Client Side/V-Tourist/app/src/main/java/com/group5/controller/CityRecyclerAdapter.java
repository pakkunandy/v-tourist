package com.group5.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group5.controller.PlacesOfCity.PlacesOfCityActivity;
import com.group5.model.City;
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
 * Created by Duy on 11-Dec-15.
 */
public class CityRecyclerAdapter extends RecyclerView.Adapter<CityRecyclerAdapter.ViewHolder> {

    ArrayList<City> arrayList;
    Context context;
    int layoutId;


    //Construtor
    public CityRecyclerAdapter(Context context, int layoutId, ArrayList<City> arrayList) {
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
    public CityRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new CityRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityRecyclerAdapter.ViewHolder holder, final int position) {


        holder.captionTitle.setText(arrayList.get(position).getName());
        holder.captionBody.setText("");

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
        ImageLoader.getInstance().displayImage(arrayList.get(position).getPicture(), holder.imgTest, dio);

        holder.imgTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable.idCityCurrent = arrayList.get(position).getCityId();
                GlobalVariable.nameCityCurent = arrayList.get(position).getName();
                Intent intent = new Intent(context.getApplicationContext(), PlacesOfCityActivity.class);
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

            imgTest = (ImageView) itemView.findViewById(R.id.imgTestCity);
            captionTitle = (TextView) itemView.findViewById(R.id.captionTitleViewCity);
            captionBody = (TextView) itemView.findViewById(R.id.captionTextViewCity);
        }
    }

}
