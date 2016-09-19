package com.pedalada.app.presenter;

import com.google.common.collect.Maps;
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


    private final CompetitionRepository competitionRepository;

    private final FixtureRepository fixtureRepository;

    private CompetitionView view;

    private BettingForm currentForm = new BettingForm();

    public CompetitionPresenter(CompetitionRepository competitionRepository, FixtureRepository fixtureRepository) {

        this.competitionRepository = competitionRepository;
        this.fixtureRepository = fixtureRepository;
    }

    @Override
    public void attachView(CompetitionView view) {

        this.view = view;

        view.showProgress();
        view.hideSubmitFormButton();

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

    }

    private void sendForm(BettingForm currentForm) {

        this.currentForm = new BettingForm();
        view.hideSubmitFormButton();

    }

    private void newBet(CompetitionView.FixtureBet fixtureBet) {

        currentForm.addBet(fixtureBet.getFixture(), fixtureBet.getBet());

        view.showSubmitFormButton();

        view.addBet(fixtureBet.getFixture(), fixtureBet.getBet());

        // todo logic to hide if no games
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
