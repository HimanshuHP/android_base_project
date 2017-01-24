package com.demo.himanshu.baseproject;

import android.app.Application;
import android.content.Context;

/**
 * Created by himanshu on 24/01/17.
 */

public class BaseApplication extends Application {
    Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public Context getAppContext() {
        return appContext;
    }
}
