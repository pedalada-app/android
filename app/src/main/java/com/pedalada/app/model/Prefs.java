package com.pedalada.app.model;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private static final String ACCESS_TOKEN = "access-token";

    private final SharedPreferences sharedPreferences;

    public Prefs(Context context) {

        this.sharedPreferences = context.getSharedPreferences("APP-PREF", Context.MODE_PRIVATE);
    }

    public String getToken() {

        return sharedPreferences.getString(ACCESS_TOKEN, null);
    }

    public void setToken(String token) {

        sharedPreferences.edit().putString(ACCESS_TOKEN, token).apply();
    }
}
