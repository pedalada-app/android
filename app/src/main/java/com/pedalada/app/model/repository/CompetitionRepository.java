package com.pedalada.app.model.repository;

import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.model.objects.Competition;

import java.util.List;

import rx.Observable;

public class CompetitionRepository {

    private final BackendService backendService;

    public CompetitionRepository(BackendService backendService) {

        this.backendService = backendService;
    }

    public Observable<List<Competition>> getCompetitionList() {

        // todo cache results

        return backendService.getCompetitionList();

    }
}
