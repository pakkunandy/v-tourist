package com.group5.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group5.model.FavoriteItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by HarleTracker on 12/8/2015.
 */
public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.MyViewHolder> {
    private LayoutInflater layoutInflater;
    List<FavoriteItem> data = Collections.emptyList();
    private Context context;

    FavoriteListAdapter(Context ctx, List<FavoriteItem> data) {
        this.context = ctx;
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_item_favorite, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FavoriteItem current = data.get(position);
        holder.imagePlace.setImageResource(current.imgPlace);
        holder.Name.setText(current.Name);
        holder.Location.setText(current.Location);
        holder.Description.setText(current.Description);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView Location;
        TextView Description;
        ImageView imagePlace;

        public MyViewHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.txt_favoriteName);
            Location = (TextView) itemView.findViewById(R.id.txt_favoriteLocation);
            Description = (TextView) itemView.findViewById(R.id.txt_favoriteDecription);
            imagePlace = (ImageView) itemView.findViewById(R.id.img_FavoritePlace);
        }
    }
}
