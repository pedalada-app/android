package com.pedalada.app.view;

import com.pedalada.app.model.Bet;
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

    void addBet(Fixture fixture, Bet bet);

    Observable<FixtureBet> bets();

    Observable<Void> submitForm();

    class FixtureBet {

        private Fixture fixture;

        private Bet bet;

        public FixtureBet(Fixture fixture, Bet bet) {

            this.fixture = fixture;
            this.bet = bet;
        }

        public Bet getBet() {

            return bet;
        }

        public Fixture getFixture() {

            return fixture;
        }
    }

}
