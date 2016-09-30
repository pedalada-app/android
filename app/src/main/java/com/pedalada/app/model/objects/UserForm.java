package com.pedalada.app.model.objects;

import com.pedalada.app.model.FixtureBet;

import java.util.List;

public class UserForm {

    private String id;

    private String name;

    private int pedaladas;

    private int expectedWinning;

    private FormStatus status;

    private List<FixtureBet> bets;

    public UserForm() {

    }

    public String getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public int getPedaladas() {

        return pedaladas;
    }

    public int getExpectedWinning() {

        return expectedWinning;
    }

    public FormStatus getStatus() {

        return status;
    }

    public List<FixtureBet> getBets () {

        return bets;
    }

    @Override
    public String toString() {

        return "UserForm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pedaladas=" + pedaladas +
                ", expectedWinning=" + expectedWinning +
                ", status=" + status +
                ", bets=" + bets +
                '}';
    }
}
