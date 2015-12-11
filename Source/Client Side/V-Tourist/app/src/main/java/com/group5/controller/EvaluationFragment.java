package com.group5.controller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group5.model.Place;
import com.group5.model.Rating;
import com.group5.service.RatingServices;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salmon on 11/27/2015.
 * Section for user evaluate
 */
public class EvaluationFragment  extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluation, container, false);
        getFormWidget(view);
        return view;
    }

    protected void getFormWidget(View view){
        recyclerView  = (RecyclerView) view.findViewById(R.id.listComment);
        commentAdapter = new CommentAdapter(getActivity(), getData());
        recyclerView.setAdapter(commentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    protected void addEvent(){

    }

    protected List<CommentItem> getData(){

        List<CommentItem> itemList = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            CommentItem commentItem = new CommentItem("Nguoi viet " + i, R.drawable.ic_avatar, "Nội dung " + i, (float)4.5);
            itemList.add(commentItem);
        }
        return  itemList;
    }

    private class LoadComment extends AsyncTask<Void, Long, List<Rating>> {


        @Override
        protected List<Rating> doInBackground(Void... params) {
            try {
                return RatingServices.getRatingList(GlobalVariable.idGlobalPlaceCurrent);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
