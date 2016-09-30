package com.pedalada.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.pedalada.app.model.objects.Fixture;

public class FixtureBet implements Parcelable {

    public static final Parcelable.Creator<FixtureBet> CREATOR = new Parcelable.Creator<FixtureBet>() {

        @Override
        public FixtureBet createFromParcel(Parcel source) {

            return new FixtureBet(source);
        }

        @Override
        public FixtureBet[] newArray(int size) {

            return new FixtureBet[size];
        }
    };

    private Fixture fixture;

    private Bet bet;

    public FixtureBet(Fixture fixture, Bet bet) {

        this.fixture = fixture;
        this.bet = bet;
    }

    protected FixtureBet(Parcel in) {

        this.fixture = in.readParcelable(Fixture.class.getClassLoader());
        int tmpBet = in.readInt();
        this.bet = tmpBet == -1 ? null : Bet.values()[tmpBet];
    }

    public Bet getBet() {

        return bet;
    }

    public Fixture getFixture() {

        return fixture;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeParcelable(this.fixture, flags);
        dest.writeInt(this.bet == null ? -1 : this.bet.ordinal());
    }

    @Override
    public String toString() {

        return "FixtureBet{" +
                "fixture=" + fixture +
                ", bet=" + bet +
                '}';
    }
}
