package com.pedalada.app;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.pedalada.app.model.Prefs;
import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.model.network.BackendServiceFactory;

import timber.log.Timber;

public class MyApplication extends Application {

    private BackendService backendService;

    private Prefs prefs;

    @Override
    public void onCreate() {

        super.onCreate();

        FacebookSdk.sdkInitialize(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        Timber.d("LOG");

        initDeps();
    }

    private void initDeps() {

        final BackendServiceFactory backendServiceFactory = new BackendServiceFactory();
        backendService = backendServiceFactory.create();

        prefs = new Prefs(this);


    }

    public static MyApplication get(Context context) {
        return ((MyApplication) context.getApplicationContext());
    }


    public BackendService getBackendService() {

        return backendService;
    }

    public Prefs getPrefs() {

        return prefs;
    }
}
