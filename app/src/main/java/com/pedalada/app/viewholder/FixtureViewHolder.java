package com.pedalada.app.viewholder;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.pedalada.app.R;
import com.pedalada.app.model.objects.Fixture;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FixtureViewHolder extends ChildViewHolder {

    @BindView(R.id.item_fixture_hometeam)
    TextView homeTeam;

    @BindView(R.id.item_fixture_awayteam)
    TextView awayTeam;

    @BindView(R.id.item_fixture_draw)
    TextView draw;

    public FixtureViewHolder(View view) {

        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(Fixture fixture) {

        homeTeam.setText(fixture.getHomeTeam().getName());

        awayTeam.setText(fixture.getAwayTeam().getName());
    }
}
