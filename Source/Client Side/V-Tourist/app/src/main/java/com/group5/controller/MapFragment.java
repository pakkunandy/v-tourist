package com.group5.controller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.group5.parser.DirectionsJSONParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Salmon on 11/27/2015.
 */
public class MapFragment  extends Fragment {


    /*
    Declare for google map
     */

    public GoogleMap googleMap; //googlemap object

    public LatLng origin = null; //Start location, update after with Object class Place
    public LatLng dest = null; //Finish location, update after with Object class Place
    public static final int REQUEST_LOCATION = 1;


    private static View view;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {


        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_map, container, false);
            if(googleMap == null){
                googleMap = ((com.google.android.gms.maps.MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
                setupMap();
            }
        } catch (InflateException e) {
    /* map is already there, just return view as it is */
        }
        return view;


        //Fragment f = (Fragment) getFragmentManager().findFragmentById(R.id.map);
        //View view = inflater.inflate(R.layout.fragment_map, container, false);

        //goolgemap




        /*
        Fragment f = (Fragment) getFragmentManager().findFragmentById(R.id.map);
        if(f == null){
            googleMap = ((com.google.android.gms.maps.MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
            setupMap();
        }
        */


        //origin = new LatLng(10.8147499,106.7091127);

       // return view;
    }

    public void setupMap(){

        dest = new LatLng(GlobalVariable.latitute,GlobalVariable.longtitute);

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        googleMap.setBuildingsEnabled(true);
        //googleMap.setInfoWindowAdapter(new MyInfoWindow(getActivity().getBaseContext(), R.layout.my_info_window));

        googleMap.addMarker(new MarkerOptions().position(dest).title(GlobalVariable.name + " => Chỉ đường")).showInfoWindow();


        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dest, 13));

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //Direction
                LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    //All location services are disabled
                    Toast.makeText(getContext(),"Vui lòng mở cho phép truy cập địa điểm hiện tại",Toast.LENGTH_LONG).show();

                }
                /*
                if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(),"Không có location",Toast.LENGTH_LONG).show();
                    // Check Permissions Now
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_LOCATION);
                }*/ else {
                    // permission has been granted, continue as usual
                    googleMap.setMyLocationEnabled(true);

                    //get my location
                    Location myLocation = googleMap.getMyLocation();
                    origin = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());

                    String url = getDirectionsUrl(origin, dest);

                    DownloadTask downloadTask = new DownloadTask();
                    downloadTask.execute(url);

                }
            }
        });

    }



    private String getDirectionsUrl(LatLng origin,LatLng dest){
        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        return url;
    }


    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }


    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> > {
        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(2);
                lineOptions.color(Color.RED);
            }

            // Drawing polyline in the Google Map for the i-th route
            googleMap.addPolyline(lineOptions);

            //set marker and focus
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin,13));
            //googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_start)).position(origin));
            //googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_goal)).position(dest));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_LOCATION) {
            if (permissions.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                googleMap.setMyLocationEnabled(true);
            } else {
                // Permission was denied. Display an error message.
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //goolgemap

        Fragment f = (Fragment) getFragmentManager().findFragmentById(R.id.map);

        if (f == null ) {
            googleMap = ((com.google.android.gms.maps.MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
            setupMap();
        }


        /*
        if(f == null){
            googleMap = ((com.google.android.gms.maps.MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
            setupMap();
        }
        */


        /*
        Fragment f = (Fragment) getFragmentManager().findFragmentById(R.id.map);
        if(googleMap == null){
            googleMap = ((com.google.android.gms.maps.MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
            setupMap();
        }
        else {
            setupMap();
        }
        */
    }




    @Override
    public void onPause() {
        super.onPause();

        /*
        if (googleMap != null) {
           // googleMap = ((com.google.android.gms.maps.MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
            getActivity().
                    getFragmentManager().beginTransaction()
                    .remove(getActivity().getFragmentManager().findFragmentById(R.id.map)).commit();
            //googleMap = null;
        }
        */



        Fragment f = (Fragment) getFragmentManager().findFragmentById(R.id.map);
        if (f != null) {
            //getFragmentManager().beginTransaction().remove(f).commit();
            getActivity().
                    getFragmentManager().beginTransaction()
                    .remove(getActivity().getFragmentManager().findFragmentById(R.id.map)).commit();
        }

    }





    @Override
    public void onDestroyView() {
        /*
        if (googleMap != null) {
           /* googleMap = ((com.google.android.gms.maps.MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
            getActivity().getFragmentManager().beginTransaction()
                    .remove(getActivity().getFragmentManager().findFragmentById(R.id.map)).commit();
            googleMap = null;
        }
    */
        super.onDestroyView();
/*
        Fragment f = (Fragment) getFragmentManager().findFragmentById(R.id.map);
        if (f != null) {
            getFragmentManager().beginTransaction().remove(f).commit();
        }
*/



    }
}
