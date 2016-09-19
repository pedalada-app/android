package com.pedalada.app.presenter;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pedalada.app.model.Bet;
import com.pedalada.app.model.FixtureBet;
import com.pedalada.app.model.objects.Fixture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

public class BettingForm implements Parcelable {

    public static final Parcelable.Creator<BettingForm> CREATOR = new Parcelable.Creator<BettingForm>() {

        @Override
        public BettingForm createFromParcel(Parcel source) {

            return new BettingForm(source);
        }

        @Override
        public BettingForm[] newArray(int size) {

            return new BettingForm[size];
        }
    };

    private Map<Fixture, Bet> betMap = Maps.newHashMap();

    private double totalOdds = 1;

    public BettingForm() {

    }

    protected BettingForm(Parcel in) {

        int betMapSize = in.readInt();
        this.betMap = new HashMap<Fixture, Bet>(betMapSize);
        for (int i = 0; i < betMapSize; i++) {
            Fixture key = in.readParcelable(Fixture.class.getClassLoader());
            int tmpValue = in.readInt();
            Bet value = tmpValue == -1 ? null : Bet.values()[tmpValue];
            this.betMap.put(key, value);
        }
    }

    public void addBet(Fixture fixture, Bet bet) {

        Timber.d("addBet() fixture=[%s], bet=[%s]", fixture, bet);

        // todo logic

        if (bet == Bet.NONE) {
            betMap.remove(fixture);

        } else {
            betMap.put(fixture, bet);
        }

    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.betMap.size());
        for (Map.Entry<Fixture, Bet> entry : this.betMap.entrySet()) {
            dest.writeParcelable(entry.getKey(), flags);
            dest.writeInt(entry.getValue() == null ? -1 : entry.getValue().ordinal());
        }
    }

    public List<FixtureBet> asFixtureBetList() {

        final ArrayList<FixtureBet> betsList = Lists.newArrayList();

        for (Map.Entry<Fixture, Bet> entry : this.betMap.entrySet()) {

            betsList.add(new FixtureBet(entry.getKey(), entry.getValue()));

        }

        return betsList;
    }

}
