package com.pedalada.app.model.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class FixtureResult implements Parcelable {

    public static final Parcelable.Creator<FixtureResult> CREATOR = new Parcelable.Creator<FixtureResult>() {

        @Override
        public FixtureResult createFromParcel(Parcel source) {

            return new FixtureResult(source);
        }

        @Override
        public FixtureResult[] newArray(int size) {

            return new FixtureResult[size];
        }
    };

    private int homeTeamGoals;

    private int awayTeamGoals;

    public FixtureResult() {

    }

    public FixtureResult(int homeTeamGoals, int awayTeamGoals) {

        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }

    protected FixtureResult(Parcel in) {

        this.homeTeamGoals = in.readInt();
        this.awayTeamGoals = in.readInt();
    }

    public int getHomeTeamGoals() {

        return homeTeamGoals;
    }

    public int getAwayTeamGoals() {

        return awayTeamGoals;
    }

    @Override
    public String toString() {

        return "FixtureResult{" +
                "homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                '}';
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.homeTeamGoals);
        dest.writeInt(this.awayTeamGoals);
    }
}
