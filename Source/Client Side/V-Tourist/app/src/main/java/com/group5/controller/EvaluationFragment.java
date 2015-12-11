package com.group5.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluation, container, false);
        //LoadComment loadComment = new LoadComment(view);
        //loadComment.execute();

        return view;
    }


    private class LoadComment extends AsyncTask<Void, Long, List<Rating>> {

        private View view;
        private RecyclerView recyclerView;
        private ProgressDialog progressDialog;
        private CommentAdapter commentAdapter;

        public LoadComment(View view){
            this.view = view;
            progressDialog = new ProgressDialog(view.getContext());
            progressDialog.setTitle("Loading");
            progressDialog.show();

            recyclerView  = (RecyclerView) view.findViewById(R.id.listComment);
        }

        @Override
        protected List<Rating> doInBackground(Void... params) {
            try {
                return RatingServices.getRatingList(GlobalVariable.idGlobalPlaceCurrent);

            } catch (ParseException e) {
                e.printStackTrace();
                Log.i("=======================", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Rating> ratings) {
            super.onPostExecute(ratings);
            commentAdapter = new CommentAdapter(getActivity(), getData(ratings));
            recyclerView.setAdapter(commentAdapter);

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            progressDialog.dismiss();
        }

        protected List<CommentItem> getData(List<Rating> ratings){
            List<CommentItem> itemList = new ArrayList<CommentItem>();
            for (int i = 0; i < ratings.size() ; i++){
                CommentItem commentItem = new CommentItem(ratings.get(i).getUserRate().getName(),
                        R.drawable.ic_avatar,
                        ratings.get(i).getComment(),
                        (float)ratings.get(i).getScore());

                itemList.add(commentItem);
            }
            return  itemList;
        }
    }
}
