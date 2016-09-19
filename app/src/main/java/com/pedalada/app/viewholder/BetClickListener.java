package com.pedalada.app.viewholder;

import com.pedalada.app.model.objects.Fixture;
import com.pedalada.app.model.Bet;

public interface BetClickListener {

    void bet(Fixture fixture, Bet bet);

}
