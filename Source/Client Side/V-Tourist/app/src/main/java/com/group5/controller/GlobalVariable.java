package com.group5.controller;

import android.view.MenuItem;

import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Created by Duy on 12/8/2015.
 */
public class GlobalVariable {
    public static String idGlobalPlaceCurrent = "1uAeJjnThk";
    public static Double longtitute ;
    public static Double latitute;
    public static String name;
    public static ArrayList<String> arrayListImageUrlCurrent;
    public static String firstImageUrl;
    public static String idCityCurrent;
    public static String nameCityCurent;

    public static void setLoginTitle (MenuItem item)
    {
        if (ParseUser.getCurrentUser() == null)
            item.setTitle("Đăng nhập");
        else
            item.setTitle("Đăng xuất");
    }
}
