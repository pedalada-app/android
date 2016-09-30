package com.pedalada.app.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.pedalada.app.R;
import com.pedalada.app.model.objects.Competition;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompetitionViewHolder extends ParentViewHolder {


    @BindView(R.id.competition_name)
    TextView compeitionnametextview;

    @BindView(R.id.competition_matchday)
    TextView competitionMatchDay;

    @BindView(R.id.competition_arrow)
    ImageView mArrowExpandImageView;

    public CompetitionViewHolder(View itemView) {

        super(itemView);

        ButterKnife.bind(this, itemView);

        mArrowExpandImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded()) {
                    mArrowExpandImageView.setImageResource(R.drawable.arrow_down);
                    collapseView();
                } else {
                    mArrowExpandImageView.setImageResource(R.drawable.arrow_down_selected);
                    expandView();
                }
            }
        });


    }

    public void bind(Competition competition) {

        competitionMatchDay.setText("Current Matchday: " + competition.getMatchday());
        compeitionnametextview.setText(competition.getName());

    }

    @Override
    public boolean shouldItemViewClickToggleExpansion() {

        return true;

    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);
        if (expanded) {
            mArrowExpandImageView.setImageResource(R.drawable.arrow_down);
        } else {
            mArrowExpandImageView.setImageResource(R.drawable.arrow_down_selected);
        }
    }
}
