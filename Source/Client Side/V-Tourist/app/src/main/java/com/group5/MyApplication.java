package com.group5;

/**
 * Created by DELL on 27/11/2015.
 */
import android.app.Application;

import com.parse.Parse;

/**
 * Created by DELL on 12/11/2015.
 */
public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "h32YTkn3076no3ou7xRiB3IOjVTQSlpB5CxtuQ6L", "EP8CAcVXGTRadBqTVYZDwsMfVfpkcUuPxSnS7fTi");
    }
}
