package com.pedalada.app.model.objects;

import java.util.Date;

public class Fixture {

    private String fixtureId;

    private TeamInfo homeTeam;

    private TeamInfo awayTeam;

    private FixtureOdds odds;

    private Date date;

    private FixtureResult result;

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
}
