package com.pedalada.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.pedalada.app.MyApplication;
import com.pedalada.app.R;
import com.pedalada.app.model.Prefs;
import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.model.objects.User;
import com.pedalada.app.presenter.LoginPresenter;
import com.pedalada.app.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;
import timber.log.Timber;

public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.main_login_button)
    LoginButton loginButton;

    private CallbackManager callbackManager;

    private LoginPresenter loginPresenter;

    private PublishSubject<LoginResult> loginResultPublishSubject = PublishSubject.create();

    public static void start(Context context) {

        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_login);

        ButterKnife.bind(this);

        initPresenter();

        initLogin();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        loginPresenter.detachView();
    }

    private void initPresenter() {

        BackendService backendService = MyApplication.get(this).getBackendService();
        Prefs prefs = MyApplication.get(this).getPrefs();
        loginPresenter = new LoginPresenter(backendService, prefs);

        loginPresenter.attachView(this);

    }

    private void initLogin() {

        callbackManager = CallbackManager.Factory.create();

        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

                Timber.d("Result %s", loginResult);

                loginResultPublishSubject.onNext(loginResult);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

                Timber.e(error, "Facebook error");

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public Observable<LoginResult> successfulLogin() {

        return loginResultPublishSubject;
    }

    @Override
    public void showLoginError() {


    }

    @Override
    public void proceedToNext() {

        MainActivity.start(this);

    }
}
