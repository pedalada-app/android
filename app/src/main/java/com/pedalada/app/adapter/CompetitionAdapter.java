package com.pedalada.app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.pedalada.app.R;
import com.pedalada.app.model.objects.Competition;
import com.pedalada.app.model.objects.Fixture;
import com.pedalada.app.viewholder.CompetitionViewHolder;
import com.pedalada.app.viewholder.FixtureViewHolder;

import java.util.List;

public class CompetitionAdapter extends ExpandableRecyclerAdapter<CompetitionViewHolder, FixtureViewHolder> {

    @NonNull
    private final List<Competition> parentItemList;

    private final LayoutInflater layoutInfalter;

    public CompetitionAdapter(Context context, @NonNull List<Competition> parentItemList) {

        super(parentItemList);

        this.parentItemList = parentItemList;
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
        childViewHolder.bind(fixture);
    }

    public void addItems(List<Competition> competitionList) {

        parentItemList.addAll(competitionList);
        notifyDataSetChanged();
    }
}
