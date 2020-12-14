package com.MarceloZometa.State;

import com.MarceloZometa.FuelType;
import com.MarceloZometa.GasPump;

public class FuelSelectionState implements State {

    public FuelSelectionState(){

    }
    @Override
    public void autoPumpStop(GasPump context) {
    }

    @Override
    public void cardSwipe(GasPump context) {
    }

    @Override
    public void hitLow(GasPump context) {
        context.setFueltype(FuelType.low);
    }

    @Override
    public void hitMed(GasPump context) {
        context.setFueltype(FuelType.Medium);
    }

    @Override
    public void hitHigh(GasPump context) {
        context.setFueltype(FuelType.High);
    }

    @Override
    public void trigger(GasPump context) {
        if(context.getFueltype() != null) {
            context.setCurrentState(new PumpingState());
        }
    }

    @Override
    public void pumpReturnLever(GasPump context) {
        context.setCurrentState( new IdleState());
        context.setFueltype(null);
        context.setCard(null);
    }
}
