package com.group5.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.*;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import com.group5.model.Bookmark;
import com.group5.model.User;
import com.group5.service.BookmarkServices;
import com.group5.service.UserServices;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener  {

    private ImageView imageViewDetail;
    /*
    * View pager on top of screen
    * */
    ImageView viewPagerDetails;


    /*
    Declare for tab and viewpager matching with it
     */
    ViewPager viewPagerContentDetail ; //Viewpager that contain the body

    TabHost tabHost; //Tab selection
    HorizontalScrollView horizontalScrollView;
   // ArrayList<Fragment> fragments; //List fragment that is the content in viewpager
    android.support.v4.view.PagerAdapter pagerAdapter = null; // custom adapter in order to  pour data(fragment in this instance) to viewpager

    FloatingActionButton fabBookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageViewDetail = (ImageView)findViewById(R.id.imageViewDetails);
        setupImageViewDetail();


        //Config for Drawer navigation - start
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Config viewPagerDetails on the top
//        viewPagerDetails = (ImageView) findViewById(R.id.viewpagerDetails);
//        initViewPagerDetails();


        //Config tabhost and viewpager
        viewPagerContentDetail = (ViewPager)findViewById(R.id.viewpagerContentDetail);
        tabHost = (TabHost)findViewById(R.id.tabHostDetails);
        horizontalScrollView = (HorizontalScrollView)findViewById(R.id.scrollView);
        //fragments = new ArrayList<Fragment>();
        initTabHost();
        initViewPagerContentDetail();

        viewPagerContentDetail.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                tabHost.setCurrentTab(position);
                //tabHost.getTabWidget().getChildTabViewAt(position).setBackgroundColor(Color.RED);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int selectedPage = tabHost.getCurrentTab();
                viewPagerContentDetail.setCurrentItem(selectedPage);

                View tabView = tabHost.getCurrentTabView();
                int scrollPostion = tabView.getLeft() - (horizontalScrollView.getWidth() - tabView.getWidth()) / 2;
                horizontalScrollView.smoothScrollTo(scrollPostion, 0);
            }
        });

        //Add view Bookmark
        fabBookmark = (FloatingActionButton) findViewById(R.id.fabBookmark);
        //Set Login to show Bookmark Button
        if(UserServices.getCurrentUser() == null) {
            fabBookmark.hide();
        }
        handleBookmark();
    }

    private void initTabHost() {
        tabHost.setup();

        String[] tabNames = {"Thông tin","Đánh giá", "Bản đồ", "Hình ảnh", "Thời tiết"};
        for(int i = 0 ; i< tabNames.length; i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getApplicationContext()));
            tabHost.addTab(tabSpec);
        }
    }



    public class FakeContent  implements TabHost.TabContentFactory{

        Context context;

        public FakeContent(Context context){
            this.context = context;
        }

        @Override
        public View createTabContent(String tag) {
            View fakeview = new View(context);
            fakeview.setMinimumHeight(0);
            fakeview.setMinimumWidth(0);
            return fakeview;
        }
    }


    private void initViewPagerContentDetail(){

        ArrayList<Fragment> fragments = new ArrayList<android.support.v4.app.Fragment>();
        //fragments.clear();
        fragments.add(new InfomationFragment());
        fragments.add(new EvaluationFragment());
        fragments.add(new MapFragment());
        fragments.add(new ImageFragment());
        fragments.add(new WeatherForecastFragment());

        pagerAdapter = new com.group5.controller.PagerAdapter(getSupportFragmentManager(),fragments);
        //pagerAdapter = new PagerAdapter(getFragmentManager(),fragments);
        //pagerAdapterTest = new PagerAdapterTest(getFragmentManager());
        viewPagerContentDetail.setAdapter(pagerAdapter);
    }

    private void initViewPagerDetails(){

        //Init for top viewpager in here

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(tabHost == null)
            initTabHost();
        if(viewPagerContentDetail == null)
            initViewPagerContentDetail();
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
        switch (id){
            case R.id.action_settings:
                break;
            case R.id.action_map:
                Intent intentMap = new Intent(DetailActivity.this, MapActivity.class);
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
        switch (id){
            case R.id.nav_home:
                //navigate to home activity
                Intent intentHome = new Intent(DetailActivity.this,MainActivity.class);
                startActivity(intentHome);
                break;
            case R.id.nav_favourite_place:
                Intent intentFavorite = new Intent(DetailActivity.this,FavoriteActivity.class);
                startActivity(intentFavorite);
                break;
            case R.id.nav_map:
                Intent intentMap = new Intent(DetailActivity.this,MapActivity.class);
                startActivity(intentMap);
                break;
            default:
                break;
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Load Image
     */
    private class LoadImage extends AsyncTask<Void, Long, String> {

        Activity activity;
        ImageView imageView;
        ProgressDialog progressDialog;

        public LoadImage(Activity activity) {
            this.activity = activity;
            progressDialog = new ProgressDialog(activity.getApplicationContext());
            progressDialog.setTitle("Loading");
            progressDialog.show();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //imageView = (ImageView) activity.findViewById(R.id.viewpagerDetails);
        }

        @Override
        protected String doInBackground(Void... params) {
            //Load Image
            return null;
        }

        @Override
        protected void onPostExecute(String strUrl) {
            super.onPostExecute(strUrl);
        }

    }

    private void handleBookmark(){
        loadFavourite();

        fabBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BookmarkServices.createBookmark(GlobalVariable.idGlobalPlaceCurrent);
                    Toast.makeText(DetailActivity.this, "Đã thêm vào danh sách yêu thích.", Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadFavourite() {
       //Load favourite
    }

    private  void setupImageViewDetail()
    {
        // UNIVERSAL IMAGE LOADER SETUP
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

        ImageLoader.getInstance().displayImage(GlobalVariable.firstImageUrl, imageViewDetail);
        imageViewDetail.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
}
