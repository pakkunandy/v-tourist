package com.group5.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Salmon on 11/24/2015.
 * Adapter for recycler view
 */
public class MyHomeRecyclerAdapter extends RecyclerView.Adapter<MyHomeRecyclerAdapter.ViewHolder> {


    ArrayList<String> arrayList;
    Context context;
    int layoutId;


    //Construtor
    public MyHomeRecyclerAdapter(Context context, int layoutId, ArrayList<String> arrayList){
        super();
        this.context = context;
        this.layoutId  = layoutId;
        this.arrayList = arrayList;
    }

    @Override
    public MyHomeRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId,parent, false);
        return new MyHomeRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHomeRecyclerAdapter.ViewHolder holder, int position) {
        holder.lblTest.setText(arrayList.get(position));
        if(position % 2 == 1){
            holder.imgTest.setBackgroundColor(Color.BLACK);
        }
        else{
            holder.imgTest.setBackgroundColor(Color.YELLOW);
        }

        holder.imgTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        public TextView lblTest;

        public ViewHolder(View itemView) {
            super(itemView);

            imgTest = (ImageView)itemView.findViewById(R.id.imgTest);
            lblTest = (TextView)itemView.findViewById(R.id.lblTest);
        }
    }
}
