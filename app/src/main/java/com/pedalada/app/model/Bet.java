package com.pedalada.app.model;

import com.google.gson.annotations.SerializedName;

public enum Bet {

    @SerializedName("1")
    HOME,

    @SerializedName("2")
    AWAY,

    @SerializedName("x")
    DRAW,

    NONE

}
