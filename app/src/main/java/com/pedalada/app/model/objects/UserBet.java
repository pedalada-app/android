package com.pedalada.app.model.objects;

import com.pedalada.app.model.Bet;

public class UserBet {

    private Bet bet;

    private double odd;

    private Fixture fixture;

    public UserBet() {

    }

    public Bet getBet() {

        return bet;
    }

    public double getOdd() {

        return odd;
    }

    public Fixture getFixture() {

        return fixture;
    }
}
