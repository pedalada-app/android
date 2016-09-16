package com.pedalada.app.presenter;

import com.facebook.login.LoginManager;
import com.pedalada.app.model.Prefs;
import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.model.objects.AuthResult;
import com.pedalada.app.model.objects.User;
import com.pedalada.app.view.LoginView;

import rx.Subscription;
import timber.log.Timber;

public class LoginPresenter extends BasePresenter<LoginView> {

    private final BackendService backendService;

    private final Prefs prefs;

    private LoginView loginView;

    public LoginPresenter(BackendService backendService, Prefs prefs) {

        this.backendService = backendService;
        this.prefs = prefs;
    }

    @Override
    public void attachView(LoginView view) {

        loginView = view;

        view.showProgress();

        final Subscription subscription = view.successfulLogin()
                                              .flatMap(result -> backendService.auth(result.getAccessToken()
                                                                                           .getToken(), null))
                                              .subscribe(this::successfulSignin, this::unsuccessfulSignin,
                                                      view::hideProgress);

        addSubscriptions(subscription);

    }

    private void unsuccessfulSignin(Throwable error) {

        Timber.e(error, "Login error");

        LoginManager.getInstance().logOut();

        loginView.showLoginError();
    }

    private void successfulSignin(AuthResult authResult) {

        Timber.d("Result: %s ", authResult);

        prefs.setToken(authResult.getToken());
        final User user = new User(authResult.getName(), authResult
                .getPhotoUrl(), authResult.getPedaladas());
        loginView.proceedToNext(user);
    }
}
