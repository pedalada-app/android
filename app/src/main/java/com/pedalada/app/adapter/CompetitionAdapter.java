package com.pedalada.app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.google.common.collect.Maps;
import com.pedalada.app.R;
import com.pedalada.app.model.Bet;
import com.pedalada.app.model.objects.Competition;
import com.pedalada.app.model.objects.Fixture;
import com.pedalada.app.viewholder.BetClickListener;
import com.pedalada.app.viewholder.CompetitionViewHolder;
import com.pedalada.app.viewholder.FixtureViewHolder;

import java.util.List;
import java.util.Map;

import timber.log.Timber;

public class CompetitionAdapter extends ExpandableRecyclerAdapter<CompetitionViewHolder, FixtureViewHolder> {

    @NonNull
    private final List<Competition> parentItemList;

    private final LayoutInflater layoutInfalter;

    private BetClickListener listener;

    private Map<String, Bet> betMap = Maps.newHashMap();

    private Map<String, Pair<Integer, Integer>> positions = Maps.newHashMap();

    public CompetitionAdapter(Context context, @NonNull List<Competition> parentItemList, BetClickListener listener) {

        super(parentItemList);

        this.parentItemList = parentItemList;
        this.listener = listener;
        this.layoutInfalter = LayoutInflater.from(context);
    }

    @Override
    public CompetitionViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {

        final View view = layoutInfalter.inflate(R.layout.item_competition, parentViewGroup, false);

        return new CompetitionViewHolder(view);


    }

    @Override
    public FixtureViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {

        final View view = layoutInfalter.inflate(R.layout.item_fixture, childViewGroup, false);

        return new FixtureViewHolder(view);

    }

    @Override
    public void onBindParentViewHolder(CompetitionViewHolder parentViewHolder, int position, ParentListItem parentListItem) {

        final Competition competition = (Competition) parentListItem;
        parentViewHolder.bind(competition);

    }

    @Override
    public void onBindChildViewHolder(FixtureViewHolder childViewHolder, int position, Object childListItem) {

        final Fixture fixture = (Fixture) childListItem;

        Bet fixBet = betMap.get(fixture.getFixtureId());

        if (fixBet == null) {
            fixBet = Bet.NONE;
        }

        childViewHolder.bind(fixture, fixBet, listener);
    }

    public void addItems(List<Competition> competitionList) {

        parentItemList.addAll(competitionList);

    }

    public void updateItems(Map<String, List<Fixture>> compToFixtures) {


        for (int i = 0, parentItemListSize = parentItemList.size(); i < parentItemListSize; i++) {

            Competition competition = parentItemList.get(i);
            final String competitionId = competition.getCompId();
            final List<Fixture> fixtures = compToFixtures.get(competitionId);
            competition.setFixtures(fixtures);

            for (int i1 = 0, fixturesSize = fixtures.size(); i1 < fixturesSize; i1++) {
                Fixture fixture = fixtures.get(i1);

                positions.put(fixture.getFixtureId(), Pair.create(i, i1));
            }

        }

        Timber.d("updateItems()");

    }

    public void addBet(Fixture fixture, Bet bet) {

        final String fixtureId = fixture.getFixtureId();
        betMap.put(fixtureId, bet);

        final Pair<Integer, Integer> fixPos = positions.get(fixtureId);
        notifyChildItemChanged(fixPos.first, fixPos.second);

    }

    public void removeAllBets() {

        betMap.clear();

    }
}
