package com.pedalada.app.model.network;

import com.google.gson.Gson;
import com.pedalada.app.BuildConfig;
import com.pedalada.app.model.Prefs;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class BackendServiceFactory {

    private static final String AUTHRIZATION_HEADER = "x-access-token";

    public BackendService create(Prefs prefs) {

        Gson gson = new Gson();

        final HttpLoggingInterceptor.Logger logger = message -> Timber.tag("OkHttp").d(message);

        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(logger)
                .setLevel(HttpLoggingInterceptor.Level.BASIC);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(chain -> {

                    final Request originalRequest = chain.request();


                    final String token = prefs.getToken();

                    if (token == null) {
                        return chain.proceed(originalRequest);
                    }

                    final Request request = originalRequest.newBuilder()
                                                           .header(AUTHRIZATION_HEADER, token)
                                                           .build();

                    return chain.proceed(request);


                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BACKEND_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .build();

        return retrofit.create(BackendService.class);

    }

}
