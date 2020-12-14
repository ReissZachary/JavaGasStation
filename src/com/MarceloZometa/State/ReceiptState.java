package com.MarceloZometa.State;

import com.MarceloZometa.GasPump;

public class ReceiptState implements State {
    @Override
    public void autoPumpStop(GasPump context) {

    }

    @Override
    public void cardSwipe(GasPump context) {

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
        //Count an extra car attended
        context.increaseCarsServed();

        context.removeCar();

        //System.out.println("^^ Printing the recipe ^^");
        context.setCurrentState(new IdleState());
    }
}
