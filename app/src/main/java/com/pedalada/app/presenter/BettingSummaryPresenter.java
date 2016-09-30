package com.pedalada.app.presenter;

import com.pedalada.app.model.Prefs;
import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.model.network.BettingForm;
import com.pedalada.app.model.objects.NewFormRequest;
import com.pedalada.app.utils.RxUtils;
import com.pedalada.app.view.BettingSummaryView;

import rx.Subscription;

public class BettingSummaryPresenter extends BasePresenter<BettingSummaryView> {

    private final BackendService backendService;

    private final Prefs prefs;

    private BettingForm bettingForm;

    private BettingSummaryView view;

    private Integer betAmount;

    public BettingSummaryPresenter(BackendService backendService, Prefs prefs) {

        this.backendService = backendService;
        this.prefs = prefs;
    }

    public void setBettingForm(BettingForm bettingForm) {

        this.bettingForm = bettingForm;
    }

    @Override
    public void attachView(BettingSummaryView view) {

        this.view = view;

        view.setMaxPedaladas(prefs.getPedaladaCount());

        view.showList(bettingForm.asFixtureBetList());

        final Subscription subscription = view.submitObservable()
                                              .subscribe(this::submitForm, RxUtils::onError);
        addSubscriptions(subscription);

        final Subscription subscription1 = view.pedaladaChoosenObs()
                                               .subscribe(this::updateTotal, RxUtils::onError);
        addSubscriptions(subscription1);
    }

    private void updateTotal(Integer betAmount) {

        this.betAmount = betAmount;

        final double expectedRevenue = bettingForm.calculateExpectedRevenue(betAmount);

        view.showExpectedRevenue(expectedRevenue);

    }

    private void submitForm(Void aVoid) {

        final NewFormRequest request = NewFormRequest.fromBettingForm(bettingForm, betAmount);

        final Subscription subscription = backendService.submitForm(request).subscribe(res -> {

            prefs.addPedalada(-betAmount);

            view.showFormSuccessfullySubmitted();

            view.hide();


        }, RxUtils::onError);
        addSubscriptions(subscription);

    }


}
