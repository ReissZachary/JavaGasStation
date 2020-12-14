package com.MarceloZometa.State;

import com.MarceloZometa.Card;
import com.MarceloZometa.GasPump;

public class IdleState implements State {

    public IdleState() {

    }

    @Override
    public void autoPumpStop(GasPump context) {

    }

    @Override
    public void cardSwipe(GasPump context) {
        //get pin from user
//        Card customerCard = context.getCardInfo();
//
//        context.setCard(customerCard);

        context.setCurrentState( new FuelSelectionState());
    }

    @Override
    public void hitLow(GasPump context) {

    }

    @Override
    public void hitMed(GasPump context) {

    }

    @Override
    public void hitHigh(GasPump context) {

    }

    @Override
    public void trigger(GasPump context) {

    }

    @Override
    public void pumpReturnLever(GasPump context) {

    }
}
