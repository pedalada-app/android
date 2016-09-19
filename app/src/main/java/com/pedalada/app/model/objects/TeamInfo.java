package com.pedalada.app.model.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class TeamInfo implements Parcelable {

    public static final Parcelable.Creator<TeamInfo> CREATOR = new Parcelable.Creator<TeamInfo>() {

        @Override
        public TeamInfo createFromParcel(Parcel source) {

            return new TeamInfo(source);
        }

        @Override
        public TeamInfo[] newArray(int size) {

            return new TeamInfo[size];
        }
    };

    private String id;

    private String name;

    public TeamInfo() {

    }

    public TeamInfo(String id, String name) {

        this.id = id;
        this.name = name;
    }

    protected TeamInfo(Parcel in) {

        this.id = in.readString();
        this.name = in.readString();
    }

    @Override
    public String toString() {

        return "TeamInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {

        return name;
    }

    public String getId() {

        return id;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.id);
        dest.writeString(this.name);
    }
}
