package com.pedalada.app.fragments;

import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.presenter.BasePresenter;
import com.pedalada.app.presenter.BettingForm;
import com.pedalada.app.utils.RxUtils;
import com.pedalada.app.view.BettingSummaryView;

import rx.Subscription;

public class BettingSummaryPresenter extends BasePresenter<BettingSummaryView> {

    private final BackendService backendService;

    private final BettingForm bettingForm;

    public BettingSummaryPresenter(BackendService backendService, BettingForm bettingForm) {

        this.backendService = backendService;

        this.bettingForm = bettingForm;
    }

    @Override
    public void attachView(BettingSummaryView view) {

        view.showList(bettingForm.asFixtureBetList());

        final Subscription subscription = view.submitObservable()
                                              .subscribe(this::submitForm, RxUtils::onError);
        addSubscriptions(subscription);

        final Subscription subscription1 = view.pedaladaChoosenObs()
                                           .subscribe(this::updateTotal, RxUtils::onError);
    }

    private void updateTotal(Integer integer) {



    }

    private void submitForm(Void aVoid) {



    }


}
