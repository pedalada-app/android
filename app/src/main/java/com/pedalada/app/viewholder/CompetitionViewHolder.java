package com.pedalada.app.viewholder;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.pedalada.app.R;
import com.pedalada.app.model.objects.Competition;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompetitionViewHolder extends ParentViewHolder {


    @BindView(R.id.competition_name)
    TextView compeitionnametextview;

    public CompetitionViewHolder(View itemView) {

        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void bind(Competition competition) {

        compeitionnametextview.setText(competition.getName());

    }
}
