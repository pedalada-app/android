package com.pedalada.app;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.pedalada.app.model.objects.TokenFcmUpdate;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        super.onTokenRefresh();

        final String token = FirebaseInstanceId.getInstance().getToken();

        Timber.d("Refresh token %s", token);

        sendRegistrationToServer(token);

    }

    private void sendRegistrationToServer(String token) {

        if (!MyApplication.get(this).getPrefs().isLoggedin()) {
            return;
        }

        MyApplication.get(this)
                     .getBackendService()
                     .updateFcmToken(new TokenFcmUpdate(token))
                     .enqueue(new Callback<ResponseBody>() {

                         @Override
                         public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                             Timber.d("Successfully updated call");
                         }

                         @Override
                         public void onFailure(Call<ResponseBody> call, Throwable t) {

                             Timber.e(t, "Failure updating token");
                         }
                     });
    }
}
