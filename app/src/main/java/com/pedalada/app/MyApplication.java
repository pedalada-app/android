package com.pedalada.app;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.pedalada.app.model.Prefs;
import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.model.objects.AuthResult;
import com.pedalada.app.model.objects.Competition;
import com.pedalada.app.model.objects.FixtureResponse;

import java.util.List;

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

//        final BackendServiceFactory backendServiceFactory = new BackendServiceFactory();
//        backendService = backendServiceFactory.create();

        Gson gson = new Gson();

        backendService = new BackendService() {

            @Override
            public Observable<AuthResult> auth(@Query("access_token") String accessToken, @Query("refreshToken") String refreshToken) {

                return Observable.just(new AuthResult("my-token", "Ada Pedal", "http://url.com/my", 500));
            }

            @Override
            public Observable<List<Competition>> getCompetitionList() {

                return Observable.just(gson.fromJson(DummyData.getCompetitionString(),
                        new TypeToken<List<Competition>>() {

                        }.getType()));
            }

            @Override
            public Observable<List<FixtureResponse>> getCurrentFixtures() {

                return Observable.just(gson.fromJson(DummyData.getFixturesString(),
                        new TypeToken<List<FixtureResponse>>() {

                        }.getType()));
            }
        };

        prefs = new Prefs(this);


    }

    public BackendService getBackendService() {

        return backendService;
    }

    public Prefs getPrefs() {

        return prefs;
    }
}
