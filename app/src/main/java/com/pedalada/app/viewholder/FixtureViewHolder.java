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
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import timber.log.Timber;

public class FixtureViewHolder extends ChildViewHolder {

    private static final ButterKnife.Action<View> CLICKABLE = (view, index) -> view.setClickable(true);

    private static final ButterKnife.Action<View> DISABLE_CLICKABLE = (view, index) -> view.setClickable(false);

    @BindViews({R.id.item_fixture_hometeam_wrapper, R.id.item_fixture_draw_wrapper, R.id.item_fixture_away_wrapper})
    List<View> wrappers;


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

        wrappers.get(0).setOnClickListener(homeBetButtonListener);
        wrappers.get(1).setOnClickListener(awayBetButtonListener);
        wrappers.get(2).setOnClickListener(drawBetButtonListener);

    }

    public void bind(Fixture fixture, Bet currentBet, BetClickListener listener) {

        Timber.d(String.format("bind() called with: fixture = [%s], currentBet = [%s], listener = [%s]", fixture, currentBet, listener));

        homeTeam.setText(fixture.getHomeTeam().getName());
        awayTeam.setText(fixture.getAwayTeam().getName());

        final FixtureResult result = fixture.getResult();
        if (fixture.getStatus() == FixtureStatus.TIMED) {

            FixtureOdds fixtureOdds = fixture.getOdds();

            if (fixtureOdds == null) {
                fixtureOdds = new FixtureOdds(1, 1, 1);
            }

            homeTeamOdds.setText(formatedOdds(fixtureOdds.getHomeWin()));
            awayTeamOdds.setText(formatedOdds(fixtureOdds.getAwayWin()));
            drawOdds.setText(formatedOdds(fixtureOdds.getDraw()));

            ButterKnife.apply(wrappers, CLICKABLE);
            ButterKnife.apply(wrappers, new ButterKnife.Action<View>() {

                @Override
                public void apply(@NonNull View view, int index) {

                    if (currentBet.ordinal() == index) {
                        view.setBackgroundResource(R.color.fixture_bet_click);
                    } else {
                        view.setBackgroundResource(0x0);
                    }

                }

            });

            updateListener(homeBetButtonListener, fixture, Bet.HOME, currentBet, listener);
            updateListener(awayBetButtonListener, fixture, Bet.AWAY, currentBet, listener);
            updateListener(drawBetButtonListener, fixture, Bet.DRAW, currentBet, listener);


        } else {

            ButterKnife.apply(wrappers, DISABLE_CLICKABLE);

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

            if (listener != null) {
                listener.bet(fixture, userBet);
            }

        }
    }
}
