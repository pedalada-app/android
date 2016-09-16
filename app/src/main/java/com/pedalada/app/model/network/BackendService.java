package com.pedalada.app.model.network;

import com.pedalada.app.model.objects.AuthResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface BackendService {

    @GET("/users/auth/facebook")
    Observable<AuthResult> auth(@Query("access_token") String accessToken, @Query("refreshToken") String refreshToken);


}
