package com.pedalada.app;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.pedalada.app.model.Prefs;
import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.model.network.BackendServiceFactory;
import com.pedalada.app.model.objects.AuthResult;
import com.pedalada.app.model.objects.Competition;
import com.pedalada.app.model.objects.FixtureResponse;
import com.pedalada.app.model.objects.FormPostResponse;
import com.pedalada.app.model.objects.NewFormRequest;
import com.pedalada.app.model.objects.UserForm;

import java.util.List;

import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import timber.log.Timber;

public class MyApplication extends Application {

    private BackendService backendService;

    private Prefs prefs;

    public static MyApplication get(Context context) {

        return ((MyApplication) context.getApplicationContext());
    }

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

        prefs = new Prefs(this);

        final BackendServiceFactory backendServiceFactory = new BackendServiceFactory();
        backendService = backendServiceFactory.create(prefs);



    }

    public BackendService getBackendService() {

        return backendService;
    }

    public Prefs getPrefs() {

        return prefs;
    }
}
