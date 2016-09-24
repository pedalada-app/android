package com.pedalada.app.model.objects;

import com.google.gson.annotations.SerializedName;
import com.pedalada.app.model.Bet;

public class FormBet {

    private double odd;

    @SerializedName("fixture")
    private String fixture;

    private Bet bet;

    public FormBet() {

    }

    public FormBet(String fixture, Bet bet, double odd) {

        this.fixture = fixture;
        this.bet = bet;
        this.odd = odd;
    }
}
