package com.pedalada.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.collect.Lists;
import com.pedalada.app.MyApplication;
import com.pedalada.app.R;
import com.pedalada.app.adapter.CompetitionAdapter;
import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.model.objects.Competition;
import com.pedalada.app.model.repository.CompetitionRepository;
import com.pedalada.app.presenter.CompetitionPresenter;
import com.pedalada.app.view.CompetitionView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompetitionFragment extends BaseFragment implements CompetitionView {

    @BindView(R.id.competition_list)
    RecyclerView recyclerView;

    private CompetitionAdapter adapter;

    private CompetitionPresenter presenter;

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

        CompetitionRepository competitionRepository = new CompetitionRepository(backendService);

        presenter = new CompetitionPresenter(competitionRepository);
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
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new CompetitionAdapter(context, Lists.newArrayList());
        recyclerView.setAdapter(adapter);

        presenter.attachView(this);


    }

    @Override
    public void showCompetition(List<Competition> competitionList) {

        adapter.addItems(competitionList);

    }
}
