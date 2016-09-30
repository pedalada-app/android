package com.pedalada.app.model.objects;

import com.google.gson.annotations.SerializedName;

public enum FormStatus {

    @SerializedName("in-progress")
    IN_PROGRESS,

    @SerializedName("winner")
    WINNER,

    @SerializedName("loser")
    LOSER;

    public String displayed() {

        switch (this) {
            case IN_PROGRESS:
                return "In Progress";
            case WINNER:
                return "Winner";
            case LOSER:
                return "Loser";
            default:
                return super.toString();
        }

    }
}
