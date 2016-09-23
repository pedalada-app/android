package com.pedalada.app.presenter;

import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.model.objects.UserForm;
import com.pedalada.app.utils.RxUtils;
import com.pedalada.app.view.FormHistoryView;

import rx.Subscription;

public class FormHistoryPresenter extends BasePresenter<FormHistoryView> {

    private final BackendService backendService;

    public FormHistoryPresenter(BackendService backendService) {

        this.backendService = backendService;
    }

    @Override
    public void attachView(FormHistoryView view) {

        final Subscription subscription = backendService.getForms()
                                                        .subscribe(view::showForms, RxUtils::onError);
        addSubscriptions(subscription);

        final Subscription subscription1 = view.onFormClicked()
                                               .map(UserForm::getId)
                                               .subscribe(view::showForm, RxUtils::onError);
        addSubscriptions(subscription1);

    }
}
