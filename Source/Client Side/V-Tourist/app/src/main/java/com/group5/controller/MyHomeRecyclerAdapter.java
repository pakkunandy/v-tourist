package com.group5.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group5.model.Place;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

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

        Bitmap image = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.nhatho);
        int gradientStartColor = Color.argb(0, 0, 0, 0);
        int gradientEndColor = Color.argb(255, 0, 0, 0);
        GradientOverImageDrawable gradientDrawable = new GradientOverImageDrawable(this.context.getResources(), image);
        gradientDrawable.setGradientColors(gradientStartColor, gradientEndColor);

        holder.imgTest.setImageDrawable(gradientDrawable);

        holder.imgTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable.idGlobalPlaceCurrent = arrayList.get(position).getPlaceId();
                Intent intent = new Intent(context.getApplicationContext(), DetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount()
    {
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
