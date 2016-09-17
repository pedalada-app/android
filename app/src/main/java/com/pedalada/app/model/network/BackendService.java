package com.pedalada.app.model.network;

import com.pedalada.app.model.objects.AuthResult;
import com.pedalada.app.model.objects.Competition;
import com.pedalada.app.model.objects.FixtureResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface BackendService {

    @GET("/users/auth/facebook")
    Observable<AuthResult> auth(@Query("access_token") String accessToken, @Query("refreshToken") String refreshToken);


    @GET("/competition")
    Observable<List<Competition>> getCompetitionList();

    @GET("/competition/fixtures/latest")
    Observable<List<FixtureResponse>> getCurrentFixtures();
}
