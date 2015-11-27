package com.group5.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /*
    View pager on top
     */
    ViewPager viewPagerHome ;



    /*
    Two recyclerView that it's content suggest user about some place.
     */
    RecyclerView recyclerNewUpdate;
    MyHomeRecyclerAdapter myHomeRecyclerAdapterNewUpdate;
    RecyclerView.LayoutManager layoutManagerNewUpdate;
    String[] strings_new_updates;
    ArrayList<String> arrayListNewUpdates;//array list string list menu


    RecyclerView recyclerHistory;
    MyHomeRecyclerAdapter myHomeRecyclerAdapterHistory;
    RecyclerView.LayoutManager layoutManagerHistory;
    String[] strings_history;
    ArrayList<String> arrayListHistory;//array list string list menu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        

        //Config for Drawer navigation - start
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        viewPagerHome = (ViewPager)findViewById(R.id.viewpagerHome);
        initViewPager();


        arrayListNewUpdates = new ArrayList<String>();
        strings_new_updates = getResources().getStringArray(R.array.newupdates);
        Collections.addAll(arrayListNewUpdates, strings_new_updates); // replace for for() or foreach

        recyclerNewUpdate = (RecyclerView)findViewById(R.id.recyclerNewUpdate);
        //use linear layout manager
        layoutManagerNewUpdate = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerNewUpdate.setLayoutManager(layoutManagerNewUpdate);

        myHomeRecyclerAdapterNewUpdate = new MyHomeRecyclerAdapter(getApplicationContext(),R.layout.custom_list_home,arrayListNewUpdates);
        recyclerNewUpdate.setAdapter(myHomeRecyclerAdapterNewUpdate);



        arrayListHistory = new ArrayList<String>();
        strings_history = getResources().getStringArray(R.array.history);
        Collections.addAll(arrayListHistory, strings_history); // replace for for() or foreach

        recyclerHistory = (RecyclerView)findViewById(R.id.recyclerHistory);
        //use linear layout manager
        layoutManagerHistory = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerHistory.setLayoutManager(layoutManagerHistory);

        myHomeRecyclerAdapterHistory = new MyHomeRecyclerAdapter(getApplicationContext(),R.layout.custom_list_home,arrayListHistory);
        recyclerHistory.setAdapter(myHomeRecyclerAdapterHistory);



    }



    private void initViewPager(){

        //Init here

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
        switch (id){
            case R.id.nav_home:
                //not do somthing
                break;
            case R.id.nav_favourite_place:
                //navigate to favourite place activity
                Intent intentFavorite = new Intent(MainActivity.this,FavoriteActivity.class);
                startActivity(intentFavorite);
                break;
            case R.id.nav_map:
                Intent intentMap = new Intent(MainActivity.this,MapActivity.class);
                startActivity(intentMap);
                break;
            default:
                    break;

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
