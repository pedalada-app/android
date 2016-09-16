package com.pedalada.app.model.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {

        @Override
        public User createFromParcel(Parcel source) {

            return new User(source);
        }

        @Override
        public User[] newArray(int size) {

            return new User[size];
        }
    };

    private String name;

    private String photoUrl;

    private int pedaladas;

    public User(String name, String photoUrl, int pedaladas) {

        this.name = name;
        this.photoUrl = photoUrl;
        this.pedaladas = pedaladas;
    }

    protected User(Parcel in) {

        this.name = in.readString();
        this.photoUrl = in.readString();
        this.pedaladas = in.readInt();
    }

    @Override
    public String toString() {

        return "User{" +
                "name='" + name + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", pedaladas='" + pedaladas + '\'' +
                '}';
    }

    public String getName() {

        return name;
    }

    public String getPhotoUrl() {

        return photoUrl;
    }

    public int getPedaladas() {

        return pedaladas;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.name);
        dest.writeString(this.photoUrl);
        dest.writeInt(this.pedaladas);
    }
}
