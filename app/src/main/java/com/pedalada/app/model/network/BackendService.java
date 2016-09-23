package com.pedalada.app.model.network;

import com.pedalada.app.model.objects.AuthResult;
import com.pedalada.app.model.objects.CheckInResponse;
import com.pedalada.app.model.objects.Competition;
import com.pedalada.app.model.objects.FixtureResponse;
import com.pedalada.app.model.objects.FormPostResponse;
import com.pedalada.app.model.objects.NewFormRequest;
import com.pedalada.app.model.objects.TokenFcmUpdate;
import com.pedalada.app.model.objects.UserForm;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface BackendService {

    @POST("/users/auth/facebook")
    Observable<AuthResult> auth(@Query("access_token") String accessToken, @Query("refreshToken") String refreshToken,
                                @Query("fcm_token") String fcmToken);

    @POST("/users/auth/fcm")
    Call<ResponseBody> updateFcmToken(@Body TokenFcmUpdate token);

    @POST("/users/checkin")
    Observable<CheckInResponse> checkIn();

    @GET("/competition")
    Observable<List<Competition>> getCompetitionList();

    @GET("/competition/fixtures/latest")
    Observable<List<FixtureResponse>> getCurrentFixtures();

    @POST("/form")
    Observable<FormPostResponse> submitForm(@Body NewFormRequest formRequest);

    @GET("/form")
    Observable<List<UserForm>> getForms();

    @GET("/form/{formId}")
    Observable<UserForm> getForm(@Path("formId") String formId);
}
