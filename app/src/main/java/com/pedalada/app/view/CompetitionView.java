package com.pedalada.app.view;

import com.pedalada.app.model.objects.Competition;

import java.util.List;

public interface CompetitionView extends BaseView {


    void showCompetition(List<Competition> competitionList);
}
