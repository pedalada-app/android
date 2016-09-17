package com.pedalada.app.model.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.google.common.collect.Lists;

import java.util.List;

public class Competition implements Parcelable, ParentListItem {


    public static final Parcelable.Creator<Competition> CREATOR = new Parcelable.Creator<Competition>() {

        @Override
        public Competition createFromParcel(Parcel source) {

            return new Competition(source);
        }

        @Override
        public Competition[] newArray(int size) {

            return new Competition[size];
        }
    };

    private String id;

    private String name;

    private String matchday;

    private String logo;

    private List<Fixture> fixtures = Lists.newArrayList();

    public Competition() {

    }

    protected Competition(Parcel in) {

        this.id = in.readString();
        this.name = in.readString();
        this.matchday = in.readString();
        this.logo = in.readString();
    }

    @Override
    public String toString() {

        return "Competition{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", matchday='" + matchday + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }

    public String getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public List<Fixture> getFixtures() {

        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {

        this.fixtures = fixtures;
    }

    public String getMatchday() {

        return matchday;
    }


    public String getLogo() {

        return logo;
    }


    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.matchday);
        dest.writeString(this.logo);
    }

    @Override
    public List<Fixture> getChildItemList() {

        return fixtures;
    }

    @Override
    public boolean isInitiallyExpanded() {

        return false;
    }
}
