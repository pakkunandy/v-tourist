package com.group5.controller;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Salmon on 11/25/2015.
 * Pager Adapter for fragment list
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragments;


    public PagerAdapter(FragmentManager supportFragmentManager, ArrayList<android.support.v4.app.Fragment> fragments) {
        super(supportFragmentManager);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }
}
