package com.pedalada.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.Lists;
import com.jakewharton.rxbinding.view.RxView;
import com.pedalada.app.MyApplication;
import com.pedalada.app.R;
import com.pedalada.app.adapter.CompetitionAdapter;
import com.pedalada.app.model.Bet;
import com.pedalada.app.model.FixtureBet;
import com.pedalada.app.model.Prefs;
import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.model.network.BettingForm;
import com.pedalada.app.model.objects.Competition;
import com.pedalada.app.model.objects.Fixture;
import com.pedalada.app.model.repository.CompetitionRepository;
import com.pedalada.app.model.repository.FixtureRepository;
import com.pedalada.app.presenter.CompetitionPresenter;
import com.pedalada.app.view.CompetitionView;
import com.pedalada.app.viewholder.BetClickListener;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;
import timber.log.Timber;

public class CompetitionFragment extends BaseFragment implements CompetitionView, BetClickListener {

    @BindView(R.id.competition_list)
    RecyclerView recyclerView;

    @BindView(R.id.competition_send_form)
    FloatingActionButton sendForm;

    @BindView(R.id.competition_pedalada)
    TextView pedaladasCount;

    private CompetitionAdapter adapter;

    private CompetitionPresenter presenter;

    private PublishSubject<FixtureBet> fixtureBetPublishSubject = PublishSubject.create();

    public static CompetitionFragment newInstance() {

        Bundle args = new Bundle();

        CompetitionFragment fragment = new CompetitionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final MyApplication myApplication = MyApplication.get(getContext());
        BackendService backendService = myApplication.getBackendService();
        final Prefs prefs = myApplication.getPrefs();

        CompetitionRepository competitionRepository = new CompetitionRepository(backendService);
        FixtureRepository fixturePreseneter = new FixtureRepository(backendService);
        presenter = new CompetitionPresenter(backendService, competitionRepository, fixturePreseneter, prefs);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.screen_competition, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        final Context context = getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CompetitionAdapter(context, Lists.newArrayList(), this);

        recyclerView.setAdapter(adapter);

        presenter.attachView(this);


    }

    @Override
    public void showCompetition(List<Competition> competitionList) {

        Timber.d("showCompetition()");

        adapter.addItems(competitionList);

        getActivity().runOnUiThread(() -> adapter.notifyParentItemRangeInserted(0, competitionList.size()));

    }

    @Override
    public void showFixtures(Map<String, List<Fixture>> compToFixtures) {

        Timber.d("showFixtures()");

        adapter.updateItems(compToFixtures);

        getActivity().runOnUiThread(() -> adapter.notifyParentItemRangeInserted(0, 12));
        getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
    }

    @Override
    public void showSubmitFormButton() {

        sendForm.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideSubmitFormButton() {

        sendForm.setVisibility(View.INVISIBLE);

    }

    @Override
    public void updateFixture(Fixture fixture, Bet bet) {

        adapter.addBet(fixture, bet);

    }

    @Override
    public Observable<FixtureBet> bets() {

        return fixtureBetPublishSubject;
    }

    @Override
    public Observable<Void> submitForm() {

        return RxView.clicks(sendForm);
    }

    @Override
    public void showSummary(BettingForm currentForm) {

        final BettingSummaryFragment summaryFragment = BettingSummaryFragment.newInstance(currentForm);

        summaryFragment.show(getActivity().getFragmentManager(), null);
    }

    @Override
    public void updatePedalada(int integer) {

        pedaladasCount.setText(String.format("Pedaladas: %s", integer));

    }

    @Override
    public void restart() {

        adapter.removeAllBets();

    }

    @Override
    public void dailyBonusMessage(int dailyChange) {

        Toast.makeText(getContext(), String.format("You received your daily bonus of %s pedaladas!", dailyChange), Toast.LENGTH_SHORT)
             .show();
    }

    @Override
    public void bet(Fixture fixture, Bet bet) {

        fixtureBetPublishSubject.onNext(new FixtureBet(fixture, bet));


    }
}
