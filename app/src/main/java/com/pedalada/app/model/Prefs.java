package com.pedalada.app.model;

import android.content.Context;
import android.content.SharedPreferences;

import rx.Observable;
import rx.subjects.PublishSubject;

public class Prefs {

    private static final String ACCESS_TOKEN = "access-token";

    private static final String PREF_PEDALADA = "pedalada";

    private static final String PREF_PHOTO_URL = "photo_url";

    private static final String PREF_NAME = "name";

    private final SharedPreferences sharedPreferences;

    private int pedaladaCount = -1;

    private PublishSubject<Integer> pedaladaSubject = PublishSubject.create();

    public Prefs(Context context) {

        this.sharedPreferences = context.getSharedPreferences("APP-PREF", Context.MODE_PRIVATE);
    }

    public String getName() {

        return sharedPreferences.getString(PREF_NAME, "");

    }

    public String getPhotoUrl() {

        return sharedPreferences.getString(PREF_PHOTO_URL, "");

    }

    public void addPedalada(int pedaladas) {

        if (pedaladaCount == -1) {
            pedaladaCount = getPedaladaCount();
        }

        pedaladaCount += pedaladas;

        pedaladaSubject.onNext(pedaladaCount);
        sharedPreferences.edit().putInt(PREF_PEDALADA, pedaladaCount).apply();
    }

    public int getPedaladaCount() {

        return sharedPreferences.getInt(PREF_PEDALADA, -1);

    }

    public Observable<Integer> getPedaladaObservable() {

        return pedaladaSubject;

    }

    public void setPedalada(int pedalada) {

        this.pedaladaCount = pedalada;

        pedaladaSubject.onNext(pedalada);
        sharedPreferences.edit().putInt(PREF_PEDALADA, pedalada).apply();

    }

    public void saveUserdata(String name, int pedaladas, String photoUrl) {

        sharedPreferences.edit()
                         .putInt(PREF_PEDALADA, pedaladas)
                         .putString(PREF_NAME, name)
                         .putString(PREF_PHOTO_URL, photoUrl)
                         .apply();

    }

    public boolean isLoggedin() {

        return getToken() != null;
    }

    public String getToken() {

        return sharedPreferences.getString(ACCESS_TOKEN, null);
    }

    public void setToken(String token) {

        sharedPreferences.edit().putString(ACCESS_TOKEN, token).apply();
    }

}
