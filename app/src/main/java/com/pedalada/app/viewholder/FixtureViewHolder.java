package com.pedalada.app.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.pedalada.app.R;
import com.pedalada.app.model.Bet;
import com.pedalada.app.model.objects.Fixture;
import com.pedalada.app.model.objects.FixtureOdds;
import com.pedalada.app.model.objects.FixtureResult;
import com.pedalada.app.model.objects.FixtureStatus;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FixtureViewHolder extends ChildViewHolder {

    @BindView(R.id.item_fixture_hometeam_wrapper)
    View homeTeamWrapper;

    @BindView(R.id.item_fixture_draw_wrapper)
    View drawWrapper;

    @BindView(R.id.item_fixture_away_wrapper)
    View awayWrapper;

    @BindView(R.id.item_fixture_hometeam)
    TextView homeTeam;

    @BindView(R.id.item_fixture_awayteam)
    TextView awayTeam;

    @BindView(R.id.item_fixture_draw)
    TextView draw;

    @BindView(R.id.item_fixture_hometeam_odds)
    TextView homeTeamOdds;

    @BindView(R.id.item_fixture_awayteam_odds)
    TextView awayTeamOdds;

    @BindView(R.id.item_fixture_draw_odds)
    TextView drawOdds;

    private BetButtonListener homeBetButtonListener;

    private BetButtonListener awayBetButtonListener;

    private BetButtonListener drawBetButtonListener;

    public FixtureViewHolder(View view) {

        super(view);
        ButterKnife.bind(this, view);

        initListeners();
    }

    private void initListeners() {


        homeBetButtonListener = new BetButtonListener();
        drawBetButtonListener = new BetButtonListener();
        awayBetButtonListener = new BetButtonListener();

        homeTeamWrapper.setOnClickListener(homeBetButtonListener);
        awayWrapper.setOnClickListener(awayBetButtonListener);
        drawWrapper.setOnClickListener(drawBetButtonListener);

    }

    public void bind(Fixture fixture, Bet currentBet, BetClickListener listener) {


        homeTeam.setText(fixture.getHomeTeam().getName());
        awayTeam.setText(fixture.getAwayTeam().getName());

        final FixtureResult result = fixture.getResult();
        if (true || fixture.getFixtureStatus() == FixtureStatus.TIMED) {

            final FixtureOdds fixtureOdds = fixture.getOdds();
            homeTeamOdds.setText(formatedOdds(fixtureOdds.getHomeWin()));
            awayTeamOdds.setText(formatedOdds(fixtureOdds.getAwayWin()));
            drawOdds.setText(formatedOdds(fixtureOdds.getDraw()));

            drawWrapper.setVisibility(View.VISIBLE);
            homeTeamWrapper.setClickable(true);
            awayWrapper.setClickable(true);

            updateListener(homeBetButtonListener, fixture, Bet.HOME, currentBet, listener);
            updateListener(awayBetButtonListener, fixture, Bet.AWAY, currentBet, listener);
            updateListener(drawBetButtonListener, fixture, Bet.DRAW, currentBet, listener);

            switch (currentBet) {
                case HOME:
                    homeTeamWrapper.setBackgroundResource(R.color.fixture_bet_click);
                    break;
                case AWAY:
                    awayWrapper.setBackgroundResource(R.color.fixture_bet_click);
                    break;
                case DRAW:
                    drawWrapper.setBackgroundResource(R.color.fixture_bet_click);
                    break;
                case NONE:
                    break;
            }
        } else {

            drawWrapper.setVisibility(View.INVISIBLE);
            homeTeamWrapper.setClickable(false);
            awayWrapper.setClickable(false);

            homeTeamOdds.setText(String.format("%d", result.getHomeTeamGoals()));
            awayTeamOdds.setText(String.format("%d", result.getAwayTeamGoals()));

        }


    }

    @NonNull
    private String formatedOdds(double odd) {

        return MessageFormat.format("({0})", odd);
    }

    private void updateListener(BetButtonListener clickBetButtonListener, Fixture fixture, Bet listenerBet, Bet currentBet, BetClickListener listener) {

        if (currentBet == listenerBet) {
            listenerBet = Bet.NONE;
        }

        clickBetButtonListener.fixture = fixture;
        clickBetButtonListener.listener = listener;
        clickBetButtonListener.userBet = listenerBet;

    }

    private final class BetButtonListener implements View.OnClickListener {

        private BetClickListener listener;

        private Fixture fixture;

        private Bet userBet;

        @Override
        public void onClick(View view) {

            if (listener == null) {
                throw new IllegalStateException("BetButtonListener is null");
            }

            listener.bet(fixture, userBet);
        }
    }
}
