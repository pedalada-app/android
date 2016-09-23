package com.pedalada.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedalada.app.R;
import com.pedalada.app.model.FixtureBet;
import com.pedalada.app.viewholder.BetSummaryViewHolder;

import java.util.List;

public class FormSummaryAdapter extends RecyclerView.Adapter<BetSummaryViewHolder> {

    private final LayoutInflater layoutInflater;

    private final List<FixtureBet> bets;

    public FormSummaryAdapter(Context context, List<FixtureBet> bets) {

        this.bets = bets;

        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public BetSummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = layoutInflater.inflate(R.layout.item_bet_summary, parent, false);

        return new BetSummaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BetSummaryViewHolder holder, int position) {

        final FixtureBet fixtureBet = bets.get(position);
        holder.bind(fixtureBet);

    }

    @Override
    public int getItemCount() {

        return bets.size();
    }

    public void showList(List<FixtureBet> betList) {

        bets.addAll(betList);

    }
}
