package com.group5.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.os.AsyncTaskCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.group5.model.Place;
import com.group5.service.DistrictServices;
import com.group5.service.PlaceServices;
import com.parse.ParseException;

import org.w3c.dom.Text;

/**
 * Created by Salmon on 11/27/2015.
 * Detail clearly about this place
 */
public class InfomationFragment  extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        DataFromOnePlace dataFromOnePlace = new DataFromOnePlace(view);
        dataFromOnePlace.execute();
        return view;
    }


    /**
     * AsyncTask
     */
    private class DataFromOnePlace extends AsyncTask<Void, Long, Place> {

        View view;
        TextView txtPlaceName;
        TextView txtAddress;
        //TextView txtPhone;
        TextView txtDescription;
        ProgressDialog dialog;

        public DataFromOnePlace(View view) {
            this.view = view;
            dialog = new ProgressDialog(view.getContext());
            dialog.setTitle("Loading");
            dialog.show();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getFormWidget(view);
        }

        @Override
        protected Place doInBackground(Void... params) {
            try {
                return PlaceServices.getPlace(GlobalVariable.idGlobalPlaceCurrent);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Place place) {
            super.onPostExecute(place);
            addDataToView(place);
        }

        /**
         * Get Form Widget
         */
        protected void getFormWidget(View v) {
            txtAddress = (TextView) v.findViewById(R.id.txtAddress);
            txtPlaceName = (TextView) v.findViewById(R.id.txtPlaceName);
            //txtPhone = (TextView) v.findViewById(R.id.txtPhone);
            txtDescription = (TextView) v.findViewById(R.id.txtDescription);
        }

        /**
         * Fill data from Place to View
         */
        protected void addDataToView(Place place){
            txtPlaceName.setText(place.getPlaceName());
            //txtPhone.setText(place.getPhone());
            txtAddress.setText(place.getAddress());
            txtDescription.setText(place.getLongDescription());
            dialog.dismiss();
        }
    }
}
