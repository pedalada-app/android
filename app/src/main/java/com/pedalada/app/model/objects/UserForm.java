package com.pedalada.app.model.objects;

import com.pedalada.app.model.FixtureBet;

import java.util.Date;
import java.util.List;

public class UserForm {

    private String id;

    private String name;

    private int pedaladas;

    private int expectedWinning;

    private FormStatus status;

    private List<FixtureBet> fixtureList;

    private Date date;

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

    public Date getDate() {

        return date;
    }

    public List<FixtureBet> getFixtureList() {

        return fixtureList;
    }
}
