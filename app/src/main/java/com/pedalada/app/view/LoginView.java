package com.pedalada.app.view;

import com.facebook.login.LoginResult;
import com.pedalada.app.model.objects.User;

import rx.Observable;

public interface LoginView extends BaseView {

    Observable<LoginResult> successfulLogin();

    void showLoginError();

    void proceedToNext();
}
