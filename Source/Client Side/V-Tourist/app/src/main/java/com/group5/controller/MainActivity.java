package com.group5.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
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

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.group5.model.City;
import com.group5.model.Place;
import com.group5.parser.DataParser;
import com.group5.service.CityServices;
import com.group5.service.PlaceServices;
import com.group5.service.UserServices;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /*
    View pager on top
     */
    ViewPager viewPagerHome;

    /*
    Two recyclerView that it's content suggest user about some place.
     */
    RecyclerView recyclerNewUpdate;
    MyHomeRecyclerAdapter myHomeRecyclerAdapterNewUpdate;
    RecyclerView.LayoutManager layoutManagerNewUpdate;
    ArrayList<Place> arrayListNewUpdates = new ArrayList<Place>();

    MenuItem loginMenuItem;

    RecyclerView recyclerCity;
    CityRecyclerAdapter cityRecyclerAdapter;
    RecyclerView.LayoutManager layoutManagerCity;
    ArrayList<City> arrayListCity = new ArrayList<City>();

    //
    private PtrClassicFrameLayout mPtrFrame;
    private ScrollView mScrollView;
    private SliderLayout mDemoSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupSlider();
        setupImageLoader();
        setupPullToRefresh();

        LoadData loadData = new LoadData();
        loadData.execute();

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
    }

    private class LoadData extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Loading");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                arrayListNewUpdates = PlaceServices.getLastedPlacesList(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK, 10);
                arrayListCity = CityServices.getCitiesList();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setupListCity();
            setupListNewUpdate();
            progressDialog.dismiss();
        }
    }

    private void setupListNewUpdate() {
        recyclerNewUpdate = (RecyclerView) findViewById(R.id.recyclerNewUpdate);
        //use linear layout manager
        layoutManagerNewUpdate = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerNewUpdate.setLayoutManager(layoutManagerNewUpdate);

        myHomeRecyclerAdapterNewUpdate = new MyHomeRecyclerAdapter(getApplicationContext(), R.layout.custom_list_home, arrayListNewUpdates);
        recyclerNewUpdate.setAdapter(myHomeRecyclerAdapterNewUpdate);
    }

    private void setupListCity() {
        recyclerCity = (RecyclerView) findViewById(R.id.recyclerHistory);
        //use linear layout manager
        layoutManagerCity = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerCity.setLayoutManager(layoutManagerCity);

        cityRecyclerAdapter = new CityRecyclerAdapter(getApplicationContext(), R.layout.custom_list_city_home, arrayListCity);
        recyclerCity.setAdapter(cityRecyclerAdapter);

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
                Intent intentMap = new Intent(MainActivity.this, MapActivity.class);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                //not do somthing
                break;
            case R.id.nav_favourite_place:
                //navigate to favourite place activity
                Intent intentFavorite = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intentFavorite);
                break;
            case R.id.nav_map:
                Intent intentMap = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intentMap);
                break;
            case R.id.nav_login:
                loginMenuItem = item;
                if (UserServices.getCurrentUser() != null)
                {
                    ParseUser.logOut();
                    item.setTitle("Đăng nhập");
                }else {
                    ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
                    startActivityForResult(builder.build(), 0);
                    //item.setTitle("Đăng xuất");
                }
                break;
            case R.id.nav_about:
                Intent intentAbout = new Intent(MainActivity.this, AboutActivity.class);
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

    private void setupSlider() {
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hồ Chí Minh", R.drawable.slide1);
        file_maps.put("Nha Trang", R.drawable.slide2);
        file_maps.put("Hạ Long", R.drawable.slide3);
        file_maps.put("Hội An", R.drawable.slide4);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);

    }

    private void setupImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this.getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
    }

    private void setupPullToRefresh() {
        //
        mScrollView = (ScrollView) findViewById(R.id.rotate_header_scroll_view);
        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_web_view_frame);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mScrollView, header);
                //return false;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            arrayListNewUpdates = PlaceServices.getLastedPlacesList(ParseQuery.CachePolicy.NETWORK_ONLY, 10);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        setupListNewUpdate();
                        mPtrFrame.refreshComplete();
                    }
                }, 1800);
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
