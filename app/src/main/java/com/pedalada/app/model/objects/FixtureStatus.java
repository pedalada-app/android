package com.pedalada.app.model.objects;

public enum FixtureStatus {

    TIMED,

    IN_PROGRESS,

    FINISHED,

    POSTPONED,

    CANCELED;

    public String displayed() {


        switch (this) {

            case TIMED:
                return "Timed";
            case IN_PROGRESS:
                return "In Progress";
            case FINISHED:
                return "Finished";
            case POSTPONED:
                return "Postponed";
            case CANCELED:
                return "Canceled";
        }
        return super.toString();
    }
}
