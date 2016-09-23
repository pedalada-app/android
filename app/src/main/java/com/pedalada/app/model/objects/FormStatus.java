package com.pedalada.app.model.objects;

import com.google.gson.annotations.SerializedName;

public enum FormStatus {

    @SerializedName("in-progress")
    IN_PROGRESS,

    @SerializedName("winner")
    WINNER,

    @SerializedName("loser")
    LOSER
}
