package com.pedalada.app.presenter;

import com.google.common.collect.Maps;
import com.pedalada.app.model.Bet;
import com.pedalada.app.model.objects.Fixture;

import java.util.Map;

import timber.log.Timber;

public class BettingForm {

    private Map<Fixture, Bet> betMap = Maps.newHashMap();

    public BettingForm() {

    }

    public void addBet(Fixture fixture, Bet bet) {

        Timber.d("addBet() fixture=[%s], bet=[%s]", fixture, bet);

        if (bet == Bet.NONE) {
            betMap.remove(fixture);
        } else {
            betMap.put(fixture, bet);
        }

    }
}
