package com.group5.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

/**
 * Created by Duy on 12/8/2015.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    private LayoutInflater layoutInflater;
    List<CommentItem> data = Collections.emptyList();
    private Context context;

    CommentAdapter(Context context, List<CommentItem> data) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_item_comment, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CommentItem current = data.get(position);
        holder.comment.setText(current.comment);
        holder.imageAvatar.setImageResource(R.drawable.ic_addresss);
        holder.ratingBar.setRating(current.rating);
        holder.userName.setText(current.userName);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        TextView comment;
        RatingBar ratingBar;
        ImageView imageAvatar;

        public MyViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.txtUserName);
            comment = (TextView) itemView.findViewById(R.id.txtComment);
            ratingBar = (RatingBar) itemView.findViewById(R.id.rtComment);
            imageAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);

        }
    }
}
