package com.pedalada.app.model.repository;

import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.model.objects.FixtureResponse;

import java.util.List;

import rx.Observable;

public class FixtureRepository {

    private final BackendService backendService;

    public FixtureRepository(BackendService backendService) {

        this.backendService = backendService;
    }

    public Observable<List<FixtureResponse>> getFixtures() {

        return backendService.getCurrentFixtures();

    }

}
