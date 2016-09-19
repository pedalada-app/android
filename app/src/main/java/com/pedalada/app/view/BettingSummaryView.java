package com.pedalada.app.view;

import com.pedalada.app.model.FixtureBet;

import java.util.List;

import rx.Observable;

public interface BettingSummaryView extends BaseView {

    void showList(List<FixtureBet> betList);

    Observable<Void> submitObservable();

    Observable<Integer> pedaladaChoosenObs();
}
