package com.group5.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group5.model.Bookmark;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

import java.util.Collections;
import java.util.List;

/**
 * Created by HarleTracker on 12/8/2015.
 */
public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.MyViewHolder> {
    private LayoutInflater layoutInflater;
    List<Bookmark> data = Collections.emptyList();
    private Context context;

    FavoriteListAdapter(Context ctx, List<Bookmark> data) {
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
        Bookmark current = data.get(position);

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
        ImageLoader.getInstance().displayImage(current.getPlace().firstImageURL, holder.imagePlace, dio);

        holder.Name.setText(current.getPlace().getPlaceName());
        holder.Location.setText(current.getPlace().getAddress());
        holder.Description.setText(current.getPlace().getPlaceDescription());
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
