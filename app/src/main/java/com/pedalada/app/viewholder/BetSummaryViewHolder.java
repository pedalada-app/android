package com.pedalada.app.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pedalada.app.R;
import com.pedalada.app.model.Bet;
import com.pedalada.app.model.FixtureBet;
import com.pedalada.app.model.objects.Fixture;
import com.pedalada.app.model.objects.TeamInfo;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BetSummaryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.bet_summary_game)
    TextView game;

    @BindView(R.id.bet_summary_bet)
    TextView summaryBet;

    public BetSummaryViewHolder(View view) {

        super(view);

        ButterKnife.bind(this, view);
    }

    public void bind(FixtureBet fixtureBet) {

        final Fixture fixture = fixtureBet.getFixture();
        final Bet bet = fixtureBet.getBet();

        game.setText(formatFixture(fixture));

        summaryBet.setText(formatBet(fixture, bet));

    }

    private static String formatFixture(Fixture fixture) {

        final TeamInfo homeTeam = fixture.getHomeTeam();
        final TeamInfo awayTeam = fixture.getAwayTeam();

        return MessageFormat.format("{0} vs {1}", homeTeam.getName(), awayTeam.getName());
    }

    private static String formatBet(Fixture fixture, Bet bet) {

        final String name = getCorrespondingName(fixture, bet);
        final double odds = fixture.getOdds().get(bet);

        return MessageFormat.format("Your bet: {0} ({1})", name, odds);

    }

    private static String getCorrespondingName(Fixture fixture, Bet bet) {

        switch (bet) {
            case HOME:
                return fixture.getHomeTeam().getName();
            case AWAY:
                return fixture.getAwayTeam().getName();
            case DRAW:
                return "Draw";
            default:
                throw new IllegalStateException("No team");
        }

    }

}
