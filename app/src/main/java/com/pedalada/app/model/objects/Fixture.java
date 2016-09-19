package com.pedalada.app.model.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Fixture implements Parcelable {

    public static final Creator<Fixture> CREATOR = new Creator<Fixture>() {

        @Override
        public Fixture createFromParcel(Parcel source) {

            return new Fixture(source);
        }

        @Override
        public Fixture[] newArray(int size) {

            return new Fixture[size];
        }
    };

    @SerializedName("_id")
    private String fixtureId;

    private TeamInfo homeTeam;

    private TeamInfo awayTeam;

    private FixtureOdds odds;

    private Date date;

    private FixtureResult result;

    private FixtureStatus fixtureStatus;

    public Fixture() {

    }

    protected Fixture(Parcel in) {

        this.fixtureId = in.readString();
        this.homeTeam = in.readParcelable(TeamInfo.class.getClassLoader());
        this.awayTeam = in.readParcelable(TeamInfo.class.getClassLoader());
        this.odds = in.readParcelable(FixtureOdds.class.getClassLoader());
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.result = in.readParcelable(FixtureResult.class.getClassLoader());
        int tmpFixtureStatus = in.readInt();
        this.fixtureStatus = tmpFixtureStatus == -1 ? null : FixtureStatus.values()[tmpFixtureStatus];
    }

    @Override
    public int hashCode() {

        return fixtureId != null ? fixtureId.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fixture fixture = (Fixture) o;

        return fixtureId != null ? fixtureId.equals(fixture.fixtureId) : fixture.fixtureId == null;

    }

    @Override
    public String toString() {

        return "Fixture{" +
                "fixtureId='" + fixtureId + '\'' +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", odds=" + odds +
                ", date=" + date +
                ", result=" + result +
                '}';
    }

    public String getFixtureId() {

        return fixtureId;
    }

    public TeamInfo getHomeTeam() {

        return homeTeam;
    }

    public TeamInfo getAwayTeam() {

        return awayTeam;
    }

    public FixtureOdds getOdds() {

        return odds;
    }

    public Date getDate() {

        return date;
    }

    public FixtureResult getResult() {

        return result;
    }

    public FixtureStatus getFixtureStatus() {

        return fixtureStatus;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.fixtureId);
        dest.writeParcelable(this.homeTeam, flags);
        dest.writeParcelable(this.awayTeam, flags);
        dest.writeParcelable(this.odds, flags);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeParcelable(this.result, flags);
        dest.writeInt(this.fixtureStatus == null ? -1 : this.fixtureStatus.ordinal());
    }
}
