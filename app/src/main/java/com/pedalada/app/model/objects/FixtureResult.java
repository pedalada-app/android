package com.pedalada.app.model.objects;

public class FixtureResult {

    private int homeTeamGoals;

    private int awayTeamGoals;

    public int getHomeTeamGoals() {

        return homeTeamGoals;
    }

    public int getAwayTeamGoals() {

        return awayTeamGoals;
    }

    @Override
    public String toString() {

        return "FixtureResult{" +
                "homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                '}';
    }
}
