package com.pedalada.app.presenter;

import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.utils.RxUtils;
import com.pedalada.app.view.FormView;

import rx.Subscription;


public class FormPresenter extends BasePresenter<FormView> {

    private final BackendService backendService;

    private String formId;

    public FormPresenter(BackendService backendService) {

        this.backendService = backendService;
    }

    public void setForm(String formId) {

        this.formId = formId;
    }

    @Override
    public void attachView(FormView view) {

        final Subscription subscription = backendService.getForm(formId)
                                                        .subscribe(view::showForm, RxUtils::onError);
        addSubscriptions(subscription);

    }
}
