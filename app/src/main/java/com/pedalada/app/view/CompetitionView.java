package com.pedalada.app.view;

import com.pedalada.app.model.Bet;
import com.pedalada.app.model.FixtureBet;
import com.pedalada.app.model.network.BettingForm;
import com.pedalada.app.model.objects.Competition;
import com.pedalada.app.model.objects.Fixture;

import java.util.List;
import java.util.Map;

import rx.Observable;

public interface CompetitionView extends BaseView {

    void showCompetition(List<Competition> competitionList);

    void showFixtures(Map<String, List<Fixture>> compToFixtures);

    void showSubmitFormButton();

    void hideSubmitFormButton();

    void updateFixture(Fixture fixture, Bet bet);

    Observable<FixtureBet> bets();

    Observable<Void> submitForm();

    void showSummary(BettingForm currentForm);

    void updatePedalada(int integer);

    void restart();

    void dailyBonusMessage(int dailyChange);

}
