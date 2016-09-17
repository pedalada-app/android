package com.pedalada.app.model.objects;

import java.util.List;

public class FixtureResponse {

    private String competitionId;

    private List<Fixture> fixtureList;

    @Override
    public String toString() {

        return "FixtureResponse{" +
                "competitionId='" + competitionId + '\'' +
                ", fixtureList=" + fixtureList +
                '}';
    }

    public String getCompetitionId() {

        return competitionId;
    }

    public List<Fixture> getFixtureList() {

        return fixtureList;
    }
}
