package com.group5.controller.PlacesOfCity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;

import com.group5.controller.AboutActivity;
import com.group5.controller.FavoriteActivity;
import com.group5.controller.GlobalVariable;
import com.group5.controller.MainActivity;
import com.group5.controller.MapActivity;
import com.group5.controller.R;
import com.group5.model.Place;
import com.group5.parser.DataParser;
import com.group5.service.CityServices;
import com.group5.service.PlaceServices;
import com.group5.service.UserServices;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Duy on 15-Dec-15.
 */
public class PlacesOfCityActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    MenuItem loginMenuItem;
    ArrayList<Place> arrayListPlaces = new ArrayList<Place>();

    private PtrClassicFrameLayout mPtrFrame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_of_city);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle(GlobalVariable.nameCityCurent);

        setupPullToRefresh();

        //Config for Drawer navigation - start
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){


            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                GlobalVariable.setLoginTitle(loginMenuItem);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loginMenuItem = navigationView.getMenu().getItem(3);

        //get data
        LoadData loadData = new LoadData();
        loadData.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_map:
                Intent intentMap = new Intent(this, MapActivity.class);
                startActivity(intentMap);
                break;

            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                //navigate to home activity
                Intent intentHome = new Intent(this,MainActivity.class);
                startActivity(intentHome);
                break;
            case R.id.nav_favourite_place:
                //navigate to favourite place activity
                Intent intentFavorite = new Intent(this, FavoriteActivity.class);
                startActivity(intentFavorite);
                break;
            case R.id.nav_map:
                Intent intentMap = new Intent(this, MapActivity.class);
                startActivity(intentMap);
                break;
            case R.id.nav_login:
                loginMenuItem = item;
                if (UserServices.getCurrentUser() != null)
                {
                    ParseUser.logOut();
                    item.setTitle("Đăng nhập");
                }else {
                    ParseLoginBuilder builder = new ParseLoginBuilder(PlacesOfCityActivity.this);
                    startActivityForResult(builder.build(), 0);
                    //item.setTitle("Đăng xuất");
                }
                break;
            case R.id.nav_about:
                Intent intentAbout = new Intent(this, AboutActivity.class);
                startActivity(intentAbout);
                break;
            default:
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 0) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                loginMenuItem.setTitle("Đăng xuất");
            }
            if (resultCode == RESULT_CANCELED) {
                // Change title

            }
        }
    }

    private class LoadData extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(PlacesOfCityActivity.this);
            progressDialog.setTitle("Loading");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                arrayListPlaces = PlaceServices.getPlacesList(GlobalVariable.idCityCurrent, 20, 0, ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setupPlacesOfCityAdapter();
            progressDialog.dismiss();
        }
    }

    private void setupPlacesOfCityAdapter() {
        mRecyclerView = (RecyclerView)findViewById(R.id.places_of_city_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PlacesOfCityAdapter(this, arrayListPlaces);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setupPullToRefresh() {
        //
        mRecyclerView = (RecyclerView) findViewById(R.id.places_of_city_recycler_view);
        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_web_view_frame);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mRecyclerView, header);
                //return false;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            arrayListPlaces = PlaceServices.getPlacesList(GlobalVariable.idCityCurrent, 20, 0, ParseQuery.CachePolicy.NETWORK_ONLY);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        setupPlacesOfCityAdapter();
                        mPtrFrame.refreshComplete();
                    }
                }, 1000);
            }
        });

        mPtrFrame.setResistance(3.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        mPtrFrame.disableWhenHorizontalMove(true);
        mPtrFrame.setEnabledNextPtrAtOnce(false);
    }
}
