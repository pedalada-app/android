package com.pedalada.app.model.objects;

public class FixtureOdds {

    private double homeWin;

    private double awayWin;

    private double draw;

    @Override
    public String toString() {

        return "FixtureOdds{" +
                "homeWin=" + homeWin +
                ", awayWin=" + awayWin +
                ", draw=" + draw +
                '}';
    }

    public double getHomeWin() {

        return homeWin;
    }

    public double getAwayWin() {

        return awayWin;
    }

    public double getDraw() {

        return draw;
    }
}
