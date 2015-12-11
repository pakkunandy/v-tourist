package com.group5.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.group5.service.PlaceServices;
import com.parse.ParseException;

import java.util.ArrayList;

/**
 * Created by Salmon on 11/27/2015.
 * Section show some images demonstrate this place
 */
public class ImageFragment  extends android.support.v4.app.Fragment {

    private ArrayList<String> arrayListImageUrl = new ArrayList<>();
    private GridView gridview;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);

        gridview = (GridView)view.findViewById(R.id.gridview);

        LoadData loadData = new LoadData();
        loadData.execute();

        return view;
    }

    private class LoadData extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                arrayListImageUrl = PlaceServices.getImageUrlList(GlobalVariable.idGlobalPlaceCurrent);
                GlobalVariable.arrayListImageUrlCurrent = arrayListImageUrl;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            gridview.setAdapter(new ImageAdapter(getContext(), arrayListImageUrl));

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    // Sending image id to FullScreenActivity
                    Intent i = new Intent(getContext(), FullScreenViewActivity.class);
                    // passing array index
                    i.putExtra("imageUrl", position);
                    getActivity().startActivity(i);
                }
            });
        }
    }
}
