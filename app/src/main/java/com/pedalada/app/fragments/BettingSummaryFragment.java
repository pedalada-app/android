package com.pedalada.app.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
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
import com.jakewharton.rxbinding.view.RxView;
import com.pedalada.app.MyApplication;
import com.pedalada.app.R;
import com.pedalada.app.adapter.FormSummaryAdapter;
import com.pedalada.app.model.FixtureBet;
import com.pedalada.app.model.Prefs;
import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.model.network.BettingForm;
import com.pedalada.app.presenter.BettingSummaryPresenter;
import com.pedalada.app.view.BettingSummaryView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;

public class BettingSummaryFragment extends DialogFragment implements BettingSummaryView {


    private static final String EXTRA_BETTING_FORM = "betting form";

    @BindView(R.id.betting_pedalada_picker)
    NumberPicker pedaladaPicker;

    @BindView(R.id.betting_pedalada_winning)
    TextView expectedRevenueTv;

    @BindView(R.id.betting_summary_send)
    Button sendFormButton;

    @BindView(R.id.betting_summary_list)
    RecyclerView recyclerView;

    private FormSummaryAdapter adapter;

    private BettingSummaryPresenter presenter;

    public static BettingSummaryFragment newInstance(BettingForm bettingForm) {

        Bundle args = new Bundle();

        args.putParcelable(EXTRA_BETTING_FORM, bettingForm);

        BettingSummaryFragment fragment = new BettingSummaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final MyApplication myApplication = MyApplication.get(getActivity());
        final BackendService backendService = myApplication.getBackendService();
        final Prefs prefs = myApplication.getPrefs();

        final BettingForm bettingForm = getArguments().getParcelable(EXTRA_BETTING_FORM);

        presenter = new BettingSummaryPresenter(backendService, prefs);
        presenter.setBettingForm(bettingForm);

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

        adapter = new FormSummaryAdapter(context, Lists.newArrayList());

        recyclerView.setAdapter(adapter);

        presenter.attachView(this);
    }

    @Override
    public void showList(List<FixtureBet> betList) {

        adapter.showList(betList);

    }

    @Override
    public Observable<Void> submitObservable() {

        return RxView.clicks(sendFormButton);
    }

    @Override
    public Observable<Integer> pedaladaChoosenObs() {

        PublishSubject<Integer> picker = PublishSubject.create();

        pedaladaPicker.setOnValueChangedListener((numberPicker, from, to) -> picker.onNext(to));

        return picker;
    }

    @Override
    public void showExpectedRevenue(double expectedRevenue) {

        expectedRevenueTv.setText(String.format("Expected revenue: %s", expectedRevenue));

    }

    @Override
    public void hide() {

        dismiss();

    }

    @Override
    public void setMaxPedaladas(int val) {

        pedaladaPicker.setMaxValue(val);
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

