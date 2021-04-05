package com.example.ad2l2;

import android.app.Application;

import com.example.ad2l2.utils.PrefsHelper;

public class App extends Application {
public  static  App instance;
public  static PrefsHelper prefsHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        prefsHelper = new PrefsHelper(this);
    }
}
