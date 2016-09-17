package com.pedalada.app.presenter;

import com.pedalada.app.model.objects.Competition;
import com.pedalada.app.model.repository.CompetitionRepository;
import com.pedalada.app.model.repository.FixtureRepository;
import com.pedalada.app.utils.RxUtils;
import com.pedalada.app.view.CompetitionView;

import java.util.List;

import rx.Observable;
import rx.Subscription;

public class CompetitionPresenter extends BasePresenter<CompetitionView> {


    private final CompetitionRepository competitionRepository;

    private final FixtureRepository fixtureRepository;

    private CompetitionView view;

    public CompetitionPresenter(CompetitionRepository competitionRepository, FixtureRepository fixtureRepository) {

        this.competitionRepository = competitionRepository;
        this.fixtureRepository = fixtureRepository;
    }

    @Override
    public void attachView(CompetitionView view) {

        this.view = view;

        view.showProgress();

        final Observable<List<Competition>> fix = competitionRepository.getCompetitionList()
                                                                         .share();
        final Subscription competitionListSubscription = fix
                                                                              .subscribe(view::showCompetition, RxUtils::onError);


        addSubscriptions(competitionListSubscription);

    }
}
