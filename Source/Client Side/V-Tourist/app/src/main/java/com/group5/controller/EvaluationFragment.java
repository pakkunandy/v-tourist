package com.group5.controller;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.group5.model.Place;
import com.group5.model.Rating;
import com.group5.service.RatingServices;
import com.group5.service.UserServices;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salmon on 11/27/2015.
 * Section for user evaluate
 */
public class EvaluationFragment  extends android.support.v4.app.Fragment {

    FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private TextView txtInfor;
    private TextView txtContentInfor;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_evaluation, container, false);
        LoadComment loadComment = new LoadComment(view);
        loadComment.execute();

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fabRating);
        txtInfor = (TextView) view.findViewById(R.id.txtInfor);
        txtContentInfor = (TextView) view.findViewById(R.id.txtContentInfor);

        if(UserServices.getCurrentUser() != null){
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RatingPostDialog ratingPostDialog = new RatingPostDialog(view);

                    ratingPostDialog.show(getFragmentManager(),"missiles");
                }
            });
            txtInfor.setVisibility(View.GONE);
        }else {
            floatingActionButton.hide();
            txtInfor.setText("Bạn phải Đăng Nhập để đánh giá");

        }


        return view;
    }


    private class LoadComment extends AsyncTask<Void, Long, List<Rating>> {

        private View view;
        private ProgressDialog progressDialog;


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
                List<Rating> rs = RatingServices.getRatingList(GlobalVariable.idGlobalPlaceCurrent);
                return  rs;

            } catch (ParseException e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Rating> ratings) {
            super.onPostExecute(ratings);
            commentAdapter = new CommentAdapter(getActivity(), getData(ratings));
            if(commentAdapter.data.size() == 0){
                txtContentInfor.setText("Hiện chưa có Đánh Giá nào");
            }else {
                txtContentInfor.setVisibility(View.GONE);
            }
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
            recyclerView.setHasFixedSize(true);
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

    public class RatingPostDialog extends DialogFragment{
        View parentView;

        public RatingPostDialog(View parentView) {
            this.parentView = parentView;

        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            // Get the layout inflater
            LayoutInflater inflater = getActivity().getLayoutInflater();

            //Get Form Widget
            final View view = inflater.inflate(R.layout.rating_dialog, null);


            builder.setTitle("Đánh giá");

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(view)
                    // Add action buttons
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // sign in the user ...
                            //Create new Rating.
                            Rating rating = new Rating();
                            rating.setComment(((EditText) view.findViewById(R.id.edtCommentPost)).getText().toString());
                            rating.setPlaceId(GlobalVariable.idGlobalPlaceCurrent);
                            rating.setScore(((RatingBar) view.findViewById(R.id.rtRatingPost)).getRating());
                            rating.setUserRate(UserServices.getCurrentUser());

                            //Post Rating to Service
                            PostComment postComment = new PostComment(view);
                            postComment.execute(rating);

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            RatingPostDialog.this.getDialog().cancel();
                        }
                    });
            return builder.create();
        }
    }

    private class PostComment extends AsyncTask<Rating, Long, List<Rating>> {

        private View view;
        private ProgressDialog progressDialog;

        public PostComment(View view){
            this.view = view;
            progressDialog = new ProgressDialog(view.getContext());
            progressDialog.setTitle("Loading");
            progressDialog.show();
        }

        @Override
        protected List<Rating> doInBackground(Rating... params) {
            try {
                RatingServices.createRating(params[0]);

                CommentItem commentItem = new CommentItem(params[0].getUserRate().getName(),
                        R.drawable.ic_avatar,
                        params[0].getComment(),
                        params[0].getScore());

                commentAdapter.data.add(commentItem);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Rating> ratings) {
            super.onPostExecute(ratings);
            commentAdapter.notifyDataSetChanged();
            progressDialog.dismiss();
        }


    }

}
