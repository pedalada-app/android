package com.pedalada.app.model.objects;

import com.google.gson.annotations.SerializedName;

public class CheckInResponse {

    private int pedaladas;

    @SerializedName("dailyChange")
    private int dailyChange;

    public int getPedaladas() {

        return pedaladas;
    }

    public int getDailyChange() {

        return dailyChange;
    }
}
