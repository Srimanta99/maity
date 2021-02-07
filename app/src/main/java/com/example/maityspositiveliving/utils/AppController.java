package com.example.maityspositiveliving.utils;

import android.app.Application;

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new SessionManager(this);
    }
}
