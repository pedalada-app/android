package com.pedalada.app.presenter;

import com.google.common.collect.Maps;
import com.pedalada.app.model.FixtureBet;
import com.pedalada.app.model.Prefs;
import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.model.network.BettingForm;
import com.pedalada.app.model.objects.Competition;
import com.pedalada.app.model.objects.Fixture;
import com.pedalada.app.model.objects.FixtureResponse;
import com.pedalada.app.model.repository.CompetitionRepository;
import com.pedalada.app.model.repository.FixtureRepository;
import com.pedalada.app.utils.RxUtils;
import com.pedalada.app.view.CompetitionView;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscription;

public class CompetitionPresenter extends BasePresenter<CompetitionView> {

    private final BackendService backendService;

    private final CompetitionRepository competitionRepository;

    private final FixtureRepository fixtureRepository;

    private final Prefs prefs;

    private CompetitionView view;

    private BettingForm currentForm = new BettingForm();

    public CompetitionPresenter(BackendService backendService, CompetitionRepository competitionRepository, FixtureRepository fixtureRepository, Prefs prefs) {

        this.backendService = backendService;
        this.competitionRepository = competitionRepository;
        this.fixtureRepository = fixtureRepository;
        this.prefs = prefs;
    }

    @Override
    public void attachView(CompetitionView view) {

        this.view = view;

        view.showProgress();
        view.hideSubmitFormButton();

        final Subscription subscription1 = backendService.checkIn().subscribe(res -> {
            prefs.setPedalada(res.getPedaladas());

            if (res.getDailyChange() > 0) {
                view.dailyBonusMessage(res.getDailyChange());
            }
        }, RxUtils::onError);
        addSubscriptions(subscription1);

        final Observable<List<Competition>> competitionList = competitionRepository.getCompetitionList()
                                                                                   .share();

        final Subscription competitionListSubscription = competitionList.subscribe(view::showCompetition, RxUtils::onError);
        addSubscriptions(competitionListSubscription);

        final Subscription fixtureSubscription = Observable.zip(fixtureRepository.getFixtures(), competitionList, (fixtureResponses, competitions) -> fixtureResponses)
                                                           .subscribe(this::onFixtureResponse, RxUtils::onError);
        addSubscriptions(fixtureSubscription);

        final Subscription betSub = view.bets()
                                        .subscribe(this::newBet, RxUtils::onError);
        addSubscriptions(betSub);

        final Subscription submitFormSub = view.submitForm().subscribe(click -> {
            sendForm(currentForm);
        }, RxUtils::onError);
        addSubscriptions(submitFormSub);

        final Subscription subscription = Observable.concat(Observable.just(prefs.getPedaladaCount()), prefs
                .getPedaladaObservable())
                                                    .subscribe(view::updatePedalada, RxUtils::onError);
        addSubscriptions(subscription);
    }

    private void sendForm(BettingForm currentForm) {

        this.currentForm = new BettingForm();
        view.hideSubmitFormButton();
        view.restart();

        view.showSummary(currentForm);

    }

    private void newBet(FixtureBet fixtureBet) {

        currentForm.addBet(fixtureBet.getFixture(), fixtureBet.getBet());
        view.updateFixture(fixtureBet.getFixture(), fixtureBet.getBet());

        if (this.currentForm.activeBets() == 0) {
            view.hideSubmitFormButton();
        } else {
            view.showSubmitFormButton();
        }
    }

    private void onFixtureResponse(List<FixtureResponse> fixtureResponses) {

        Map<String, List<Fixture>> fixtures = Maps.newHashMap();

        for (FixtureResponse fixtureResponse : fixtureResponses) {
            fixtures.put(fixtureResponse.getCompetitionId(), fixtureResponse
                    .getFixtures());
        }

        view.showFixtures(fixtures);
    }


}
