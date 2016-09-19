package com.pedalada.app.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.pedalada.app.MyApplication;
import com.pedalada.app.R;
import com.pedalada.app.adapter.BettingSummaryAdapter;
import com.pedalada.app.model.FixtureBet;
import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.view.BettingSummaryView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

public class BettingSummaryFragment extends DialogFragment implements BettingSummaryView {

    private static final String EXTRA_BETS = "bets";

    @BindView(R.id.betting_pedalada_picker)
    NumberPicker pedaladaPicker;

    @BindView(R.id.betting_pedalada_winning)
    TextView total;

    @BindView(R.id.betting_summary_send)
    Button sendFormButton;

    @BindView(R.id.betting_summary_list)
    RecyclerView recyclerView;

    private BettingSummaryAdapter adapter;

    private BettingSummaryPresenter presenter;

    public static BettingSummaryFragment newInstance(List<FixtureBet> bets) {

        Bundle args = new Bundle();

        args.putParcelableArrayList(EXTRA_BETS, Lists.newArrayList(bets));

        BettingSummaryFragment fragment = new BettingSummaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final BackendService backendService = MyApplication.get(getActivity()).getBackendService();

        final ArrayList<FixtureBet> bets = getArguments().getParcelableArrayList(EXTRA_BETS);

        presenter = new BettingSummaryPresenter(backendService, bets);
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();

        presenter.detachView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.screen_betting_summary, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        final Context context = getActivity();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new BettingSummaryAdapter(context, Lists.newArrayList());

        recyclerView.setAdapter(adapter);

        presenter.attachView(this);
    }

    @Override
    public void showList(List<FixtureBet> betList) {

    }

    @Override
    public Observable<Void> submitObservable() {

        return null;
    }


    @Override
    public void showProgress() {

        throw new IllegalStateException("Not supported");

    }

    @Override
    public void hideProgress() {

        throw new IllegalStateException("Not supported");
    }

}

