package com.pedalada.app.model.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.pedalada.app.model.Bet;

public class FixtureOdds implements Parcelable {

    public static final Parcelable.Creator<FixtureOdds> CREATOR = new Parcelable.Creator<FixtureOdds>() {

        @Override
        public FixtureOdds createFromParcel(Parcel source) {

            return new FixtureOdds(source);
        }

        @Override
        public FixtureOdds[] newArray(int size) {

            return new FixtureOdds[size];
        }
    };

    private double homeWin;

    private double awayWin;

    private double draw;

    public FixtureOdds() {

    }

    public FixtureOdds(double homeWin, double awayWin, double draw) {

        this.homeWin = homeWin;
        this.awayWin = awayWin;
        this.draw = draw;
    }

    protected FixtureOdds(Parcel in) {

        this.homeWin = in.readDouble();
        this.awayWin = in.readDouble();
        this.draw = in.readDouble();
    }

    @Override
    public String toString() {

        return "FixtureOdds{" +
                "homeWin=" + homeWin +
                ", awayWin=" + awayWin +
                ", draw=" + draw +
                '}';
    }

    public double getHomeWin() {

        return homeWin;
    }

    public double getAwayWin() {

        return awayWin;
    }

    public double getDraw() {

        return draw;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeDouble(this.homeWin);
        dest.writeDouble(this.awayWin);
        dest.writeDouble(this.draw);
    }

    public double get(Bet bet) {

        switch (bet) {
            case HOME:
                return homeWin;
            case AWAY:
                return awayWin;
            case DRAW:
                return draw;
            default:
                throw new IllegalStateException("No odds");
        }

    }
}
