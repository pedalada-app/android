package com.pedalada.app.model.objects;

import java.util.List;

public class FixtureResponse {

    private String competitionId;

    private List<Fixture> fixtures;

    public FixtureResponse() {

    }

    public FixtureResponse(String competitionId, List<Fixture> fixtures) {

        this.competitionId = competitionId;
        this.fixtures = fixtures;
    }

    @Override
    public String toString() {

        return "FixtureResponse{" +
                "competitionId='" + competitionId + '\'' +
                ", fixtures=" + fixtures +
                '}';
    }

    public String getCompetitionId() {

        return competitionId;
    }

    public List<Fixture> getFixtures() {

        return fixtures;
    }
}
