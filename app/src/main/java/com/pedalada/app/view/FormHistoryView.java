package com.pedalada.app.view;

import com.pedalada.app.model.objects.UserForm;

import java.util.List;

import rx.Observable;

public interface FormHistoryView extends BaseView {

    void showForms(List<UserForm> userForms);

    Observable<UserForm> onFormClicked();

    void showForm(String s);
}
