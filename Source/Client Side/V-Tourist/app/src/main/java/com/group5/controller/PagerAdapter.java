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

//    @Override
//    public int getItemPosition(Object object) {
//        //Utils.DummyItem dummyItem = (Utils.DummyItem) ((View) object).getTag();
//        //int position = mDummyItems.indexOf(dummyItem);
////        if (position >= 0) {
////            // The current data matches the data in this active fragment, so let it be as it is.
////            return position;
////        } else {
////            // Returning POSITION_NONE means the current data does not matches the data this fragment is showing right now.  Returning POSITION_NONE constant will force the fragment to redraw its view layout all over again and show new data.
////            return POSITION_NONE;
////        }
//        return POSITION_NONE;
//    }
}
