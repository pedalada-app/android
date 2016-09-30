package com.pedalada.app.model.objects;

import com.google.common.collect.Lists;
import com.pedalada.app.model.Bet;
import com.pedalada.app.model.FixtureBet;
import com.pedalada.app.model.network.BettingForm;
import com.pedalada.app.utils.DateUtils;

import java.util.Date;
import java.util.List;

public class NewFormRequest {

    private List<FormBet> bets;

    private int pedaladas;

    private String name = DateUtils.formatDate(new Date());

    private int expectedWinning;

    public NewFormRequest() {

    }

    private NewFormRequest(List<FormBet> formBet, int pedaladas, int expectedWinning) {

        this.bets = formBet;
        this.pedaladas = pedaladas;
        this.expectedWinning = expectedWinning;
    }

    public static NewFormRequest fromBettingForm(BettingForm bettingForm, int betAmmount) {

        final List<FixtureBet> bets = bettingForm.asFixtureBetList();

        List<FormBet> formBets = Lists.newArrayList();

        for (FixtureBet bet : bets) {

            final Fixture fixture = bet.getFixture();
            final String fixtureId = fixture.getFixtureId();
            final Bet userBet = bet.getBet();
            final double odd = fixture.getOdds().get(userBet);
            final FormBet formBet = new FormBet(fixtureId, userBet, odd);
            formBets.add(formBet);

        }

        final int expectedRevenue = bettingForm.calculateExpectedRevenue(betAmmount);

        return new NewFormRequest(formBets, betAmmount, expectedRevenue);

    }
}
